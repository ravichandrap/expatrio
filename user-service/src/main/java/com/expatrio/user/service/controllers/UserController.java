package com.expatrio.user.service.controllers;

import com.expatrio.user.service.beans.UserDetails;
import com.expatrio.user.service.beans.UserProfile;
import com.expatrio.user.service.exception.UserForbidden;
import com.expatrio.user.service.service.UserService;
import com.expatrio.user.service.util.WebClientAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    WebClientAPI webClientAPI;

    @Autowired
    UserService service;

    @PostMapping("/validate")
    public ResponseEntity<UserDetails> validate(@RequestBody UserProfile userProfile) {
        logger.info("===== get with email: Authorization:{} =======", userProfile.toString());

        UserDetails user = service.validate(userProfile);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

    }

    @PostMapping("/validate/email")
    public ResponseEntity<Boolean> validateByEmail(@RequestHeader(name = "Authorization") String jwtId,
                                                   @RequestBody String email) {
        logger.info("===== get with email: Authorization:{} =======", email);

        service.getByEmail(email);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDetails> getById(@RequestHeader(name = "Authorization") String jwtId,
                                               @PathVariable Long id) {
        logger.info("===== get with id: {} : Authorization:{}=======", id, jwtId);
        UserDetails user = service.get(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<UserDetails> create(@RequestHeader(name = "Authorization") String jwtId,
                                              @RequestBody UserDetails userDetails) {
        logger.info("===== PostMapping Update User =======Authorization:{}", jwtId);
        UserDetails save = service.save(userDetails);
        return new ResponseEntity(new UserDetailsResp(jwtId,save ), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/role/{role}")
    public UserDetailsResponse getAllUsersByRole(@RequestHeader(name = "Authorization") String authorization,
                                               @PathVariable String role) {
        logger.info("===== getAllUsersByRole Authorization:{}=======", authorization);
        return UserDetailsResponse.of(authorization, service.get(role));
    }

    @GetMapping
    public UserDetailsResponse getAll(@RequestHeader(name = "Authorization") String authorization) {
        logger.info("===== getAllUsersByRole =======::::Authorization: {}", authorization);
        return UserDetailsResponse.of(authorization, service.get());
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