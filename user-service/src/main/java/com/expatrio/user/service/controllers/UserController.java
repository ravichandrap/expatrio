package com.expatrio.user.service.controllers;

import com.expatrio.user.service.beans.UserDetails;
import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.entities.UserEntity;
import com.expatrio.user.service.exception.InvalidCredentialsException;
import com.expatrio.user.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/user")

public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @PostMapping("/validate")
    public ResponseEntity<UserDetails> validate(@RequestBody UserProfile userProfile) {
        logger.info("===== get with email: Authorization: =======");

        UserDetails userDetails = getUserDetails(service.validate(userProfile));
        return new ResponseEntity<>(userDetails, HttpStatus.ACCEPTED);
    }

    @PostMapping("/validate/email")
    public ResponseEntity<Boolean> validateByEmail(@RequestHeader(name = "Authorization") String authorization,
                                                   @RequestBody String email) {
        logger.info("===== get with email: Authorization:{} =======", email);

        UserEntity userEntity = service.getByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Not found"));
        return new ResponseEntity<>(userEntity != null, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<UserDetailsResp> create(@RequestHeader(name = "Authorization") String jwtId,
                                              @RequestBody UserDetails userDetails) {
        logger.info("===== PostMapping Update User =======Authorization:{}", jwtId);
        UserEntity save = service.save(getUserDetails(userDetails));
        UserDetails saveUser = getUserDetails(save);
        return new ResponseEntity<>(new UserDetailsResp(jwtId,saveUser ), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/role/{role}")
    public UserDetailsResponse getAllUsersByRole(@RequestHeader(name = "Authorization") String authorization,
                                               @PathVariable String role) {
        logger.info("===== getAllUsersByRole Authorization:{}=======", authorization);
        List<UserEntity> users
                = role.equalsIgnoreCase("all")
                  ? service.get(): service.get(role);
        return UserDetailsResponse.of(authorization, getCollectUserDetails(users));
    }

    @GetMapping
    public UserDetailsResponse getAll(@RequestHeader(name = "Authorization") String authorization) {
        logger.info("===== getAllUsersByRole =======::::Authorization: {}", authorization);
        return UserDetailsResponse.of(authorization, getCollectUserDetails(service.get()));
    }

    private List<UserDetails> getCollectUserDetails(List<UserEntity> users) {
        return users.stream().map(this::getUserDetails).collect(Collectors.toList());
    }

    private UserEntity getUserDetails(UserDetails user) {
        UserEntity entity = new UserEntity();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, entity);
        return entity;
    }

    private UserDetails getUserDetails(UserEntity user) {
        UserDetails userDetails = new UserDetails();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, userDetails);
        return userDetails;
    }
}

class JWTAuthorization {
    private String authorization;

    public JWTAuthorization(String authorization) {
        this.authorization = authorization;
    }
    public String getAuthorization() {
        return authorization;
    }
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}

class UserDetailsResponse extends JWTAuthorization {

    private List<UserDetails> users;

    public UserDetailsResponse(String authorization, List<UserDetails> users) {
        super(authorization);
        this.users = users;
    }

    public static UserDetailsResponse of(String authorization, List<UserDetails> users) {
        return new UserDetailsResponse(authorization, users);
    }

    public List<UserDetails> getUsers() {
        return users;
    }
    public void setUsers(List<UserDetails> users) {
        this.users = users;
    }
}

class UserDetailsResp extends JWTAuthorization {
    private UserDetails user;

    public UserDetailsResp(String authorization, UserDetails user) {
        super(authorization);
        this.user = user;
    }

    public UserDetails getUser() {
        return user;
    }
    public void setUser(UserDetails user) {
        this.user = user;
    }
}