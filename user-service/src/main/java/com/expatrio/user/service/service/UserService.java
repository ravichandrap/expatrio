package com.expatrio.user.service.service;

import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.Role;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.exception.InvalidCredentialsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expatrio.user.service.beans.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDetails save(UserDetails userDetails) {
        UserEntity userEntity = getUserDetails(userDetails);
        UserEntity saveEntity = repository.save(userEntity);
        return getUserDetails(saveEntity);
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
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, userDetails);
        return userDetails;
    }

    private UserEntity getUserDetails(UserDetails user) {
        UserEntity entity = new UserEntity();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, entity);
        return entity;
    }

    public List<UserDetails> get() {
        return getCollectUserDetails(repository.findAll());
    }

    public List<UserDetails> get(String role) {
        if(role.equalsIgnoreCase("all"))
            return getCollectUserDetails(repository.findAll());
        return getCollectUserDetailsByRole(role, repository.findAll());
    }

    private List<UserDetails> getCollectUserDetailsByRole(String role, List<UserEntity> all) {
        return all.stream()
                .filter(userEntity -> userEntity.getRoles().stream().anyMatch(role1 -> role1.getName().equalsIgnoreCase(role)))
                .map(this::getUserDetails)
                .collect(Collectors.toList());
    }

    private List<UserDetails> getCollectUserDetails(List<UserEntity> users) {
        return users.stream().map(this::getUserDetails).collect(Collectors.toList());
    }

    public UserDetails validate(UserProfile userProfile) {
        return getUserDetails(repository.findByEmailAndPassword(userProfile.getEmail(), userProfile.getPassword()));
    }
}
