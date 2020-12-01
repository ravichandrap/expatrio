package com.expatrio.user.service.controllers;

import com.expatrio.user.service.beans.UserDetails;
import com.expatrio.user.service.beans.UserProfile;
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
        logger.info("===== get with email: {} =======", userProfile.toString());

        UserDetails user = service.validate(userProfile);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/validate/email")
    public ResponseEntity<Boolean> validateByEmail(@RequestBody String email) {
        logger.info("===== get with email: {} =======", email);

        service.getByEmail(email);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDetails> get(@RequestHeader(name = "jwt") String jwtId,
                                           @PathVariable String email) {
        logger.info("===== get with email: {} =======", email);

        UserDetails user = service.getByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDetails> getById(@RequestHeader(name = "jwt") String jwtId,
                                               @PathVariable Long id) {
        logger.info("===== get with id: {} =======", id);
        UserDetails user = service.get(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<UserDetails> create(@RequestHeader(name = "jwt") String jwtId,
                                              @RequestBody UserDetails userDetails) {
        logger.info("===== create =======");
        return new ResponseEntity<>(service.save(userDetails), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<UserDetails> update(@RequestHeader(name = "jwt") String jwtId,
                                              @RequestBody UserDetails userDetails) {
        logger.info("===== update =======");
        return new ResponseEntity<>(service.save(userDetails), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/role/{role}")
    public List<UserDetails> getAllUsersByRole(@RequestHeader(name = "jwt") String jwtId, @PathVariable String role) {
        logger.info("===== getAllUsersByRole =======");
        return service.getByRole(role);
    }

    @GetMapping
    public List<UserDetails> getAll(@RequestHeader(name = "Authorization") String jwt) {
        logger.info("===== getAllUsersByRole =======::::{}", jwt);

        return service.get();
    }
}
