package com.expatrio.auth.controller;

import com.expatrio.auth.beans.AuthRequest;
import com.expatrio.auth.beans.AuthResponse;
import com.expatrio.auth.exception.UserNotFoundRuntimeException;
import com.expatrio.auth.util.JwtUtil;
import com.expatrio.auth.util.WebClientAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthServiceController {

    static final Logger logger = LoggerFactory.getLogger(AuthServiceController.class);

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    WebClientAPI webClientAPI;

    @PostMapping("/creartetoken")
    public ResponseEntity<AuthResponse> create(@RequestBody AuthRequest user) throws IOException, InterruptedException {
        logger.debug("AuthenticationRequest: {} ", user);
        AuthRequest userProfile = webClientAPI.validate(user);
        final String jwt = jwtTokenUtil.generateToken(userProfile.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt, userProfile));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateLoginToken(@RequestHeader(name = "Authorization") String jwt)
            throws IOException, InterruptedException {
        final String username = jwtTokenUtil.extractUsername(jwt);

        if (webClientAPI.getUserFromUserService(username)) {
            return new ResponseEntity<>(jwtTokenUtil.validateToken(jwt, username), HttpStatus.OK);
        } else {
            throw new UserNotFoundRuntimeException(username);
        }
    }
}

