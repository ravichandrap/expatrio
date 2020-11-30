package com.expatrio.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class AuthServiceController {

    @GetMapping
    public ResponseEntity<String> all() {
        System.out.println("------------- all ----------------");

        return new ResponseEntity<>("Welcome all user admin", HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<String>create() {
        System.out.println("------------- all ----------------");

        return new ResponseEntity<>("Welcome create user admin", HttpStatus.OK);
    }
}
