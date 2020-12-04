package com.expatrio.user.service.service;

import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.exception.InvalidCredentialsException;
import com.expatrio.user.service.exception.UserNotFoundRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundRuntimeException(String.valueOf(id));
        }
    }


    public List<UserEntity> get() {
        return repository.findAll();
    }

    public UserEntity get(Long id) {
        Optional<UserEntity> optional = repository.findById(id);
        return optional.orElseThrow(()->new InvalidCredentialsException("Not Found"));
    }

    public List<UserEntity> get(String role) {
        List<UserEntity> all = repository.findAll();
        return getCollectUserDetailsByRole(role, all);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserEntity validate(UserProfile userProfile) {
        Optional<UserEntity> userEntity = repository.findByEmailAndPassword(userProfile.getEmail(), userProfile.getPassword());
        return userEntity.orElseThrow(()->new InvalidCredentialsException("Not found"));
    }

    private List<UserEntity> getCollectUserDetailsByRole(String role, List<UserEntity> all) {
        return all.stream()
                .filter(userEntity -> userEntity.getRoles().stream().anyMatch(role1 -> role1.getName().equalsIgnoreCase(role)))
                .collect(Collectors.toList());
    }
}
