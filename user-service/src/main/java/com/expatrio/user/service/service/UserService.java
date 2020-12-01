package com.expatrio.user.service.service;

import com.expatrio.user.service.beans.RoleDetails;
import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.exception.InvalidCredentialsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expatrio.user.service.beans.UserDetails;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDetails save(UserDetails userDetails) {
        return getUserDetails(repository.save(getUserDetails(userDetails)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UserDetails get(Long id) {
        Optional<UserEntity> optional = repository.findById(id);
        return getUserDetails(optional);
    }

    public UserDetails getByEmail(String email) {
        Optional<UserEntity> optional = repository.findByEmail(email);
        return getUserDetails(optional);
    }

    private UserDetails getUserDetails(Optional<UserEntity> optional) {
        UserEntity user = optional.orElseThrow(()->new InvalidCredentialsException("Not Found"));
        return getUserDetails(user);
    }

    private UserDetails getUserDetails(UserEntity user) {
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(user, userDetails);
        userDetails.setRoles(copyRoles(user.getRoles()));
        return userDetails;
    }

    private Set<RoleDetails> copyRoles(Set<Role> roles) {
        return roles.stream().map(RoleDetails::new).collect(Collectors.toSet());
    }
    private Set<Role> copyRolesFromRoleDetails(Set<RoleDetails> roles) {
        return roles.stream().map(RoleDetails::of).collect(Collectors.toSet());
    }

    private UserEntity getUserDetails(UserDetails user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        entity.setRoles(copyRolesFromRoleDetails(user.getRoles()));
        return entity;
    }

    public List<UserDetails> get() {
        return getCollectUserDetails(repository.findAll());
    }

    public List<UserDetails> getByRole(String role) {
        return getCollectUserDetails(repository.findByRoles(role));
    }

    private List<UserDetails> getCollectUserDetails(List<UserEntity> users) {
        return users.stream().map(this::getUserDetails).collect(Collectors.toList());
    }

    public UserDetails validate(UserProfile userProfile) {
        return getUserDetails(repository.findByEmailAndPassword(userProfile.getEmail(), userProfile.getPassword()));
    }
}
