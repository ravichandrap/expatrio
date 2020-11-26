package com.expatrio.user.service.controllers;

import com.expatrio.user.service.beans.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody UserDetails userDetails) {
        logger.info("===== login =======");
        return new ResponseEntity<>(userDetails, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<UserDetails> create(@RequestBody UserDetails userDetails) {
        logger.info("===== create =======");
        return new ResponseEntity<>(userDetails, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public List<UserDetails> update(@RequestBody UserDetails userDetails) {
        logger.info("===== update =======");
        return List.of(userDetails);
    }

    @DeleteMapping("/{id}")
    public List<UserDetails> delete(@PathVariable Long id) {
        logger.info("===== delete =======");
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        return List.of(userDetails);
    }

    @DeleteMapping("/{id}/role/{role}")
    public List<UserDetails> deleteByRole(@PathVariable Long id,
                                          @PathVariable String role) {
        logger.info("===== deleteByRole =======");
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        userDetails.setRole(role);
        return List.of(userDetails);
    }

    @GetMapping("/role/{role}")
    public List<UserDetails> getAllUsersByRole(@PathVariable String role) {
        logger.info("===== getAllUsersByRole =======");
        UserDetails userDetails = new UserDetails();
        userDetails.setRole(role);
        return List.of(userDetails);
    }

    @GetMapping("/{id}/role/{role}")
    public List<UserDetails> getUserByRole(@PathVariable Long id,
                                           @PathVariable String role) {
        logger.info("===== getUserByRole =======");
        UserDetails userDetails = new UserDetails();
        userDetails.setRole(role);
        userDetails.setId(id);
        return List.of(userDetails);
    }


}
