package com.expatrio.user.service.controllers;

import com.expatrio.user.service.beans.User;
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
    public ResponseEntity<User> login(@RequestBody User user) {
        logger.info("===== login =======");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        logger.info("===== create =======");
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public List<User> update(@RequestBody User user) {
        logger.info("===== update =======");
        return List.of(user);
    }

    @DeleteMapping("/{id}")
    public List<User> delete(@PathVariable Long id) {
        logger.info("===== delete =======");
        User user = new User();
        user.setId(id);
        return List.of(user);
    }

    @DeleteMapping("/{id}/role/{role}")
    public List<User> deleteByRole(@PathVariable Long id,
                                   @PathVariable String role) {
        logger.info("===== deleteByRole =======");
        User user = new User();
        user.setId(id);
        user.setRole(role);
        return List.of(user);
    }

    @GetMapping("/role/{role}")
    public List<User> getAllUsersByRole(@PathVariable String role) {
        logger.info("===== getAllUsersByRole =======");
        User user = new User();
        user.setRole(role);
        return List.of(user);
    }

    @GetMapping("/{id}/role/{role}")
    public List<User> getUserByRole(@PathVariable Long id,
                                    @PathVariable String role) {
        logger.info("===== getUserByRole =======");
        User user = new User();
        user.setRole(role);
        user.setId(id);
        return List.of(user);
    }


}
