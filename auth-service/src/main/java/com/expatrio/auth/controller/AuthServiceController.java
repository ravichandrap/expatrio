package com.expatrio.auth.controller;

import com.expatrio.auth.beans.AuthRequest;
import com.expatrio.auth.beans.AuthResponse;
import com.expatrio.auth.beans.UserDetails;
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
@RequestMapping("/api/v1/auth")
public class AuthServiceController {

    static final Logger logger = LoggerFactory.getLogger(AuthServiceController.class);

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    WebClientAPI webClientAPI;

    @PostMapping("/create")
    public ResponseEntity<AuthResponse> create(@RequestBody AuthRequest user) throws IOException, InterruptedException {
        logger.debug("AuthenticationRequest: {} ", user);
        UserDetails userProfile = webClientAPI.validate(user);
        final String jwt = jwtTokenUtil.generateToken(userProfile.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwt, userProfile));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateLoginToken(@RequestHeader(name = "Authorization") String jwt)
            throws IOException, InterruptedException {
        if(jwt.startsWith("Bearer")) {
            final String token = jwt.substring(7);
            final String username = jwtTokenUtil.extractUsername(token);

            if (webClientAPI.getUserFromUserService(username) && jwtTokenUtil.validateToken(token, username)) {

                return new ResponseEntity<>(jwtTokenUtil.generateToken(username), HttpStatus.OK);
            }
        }
        throw new UserNotFoundRuntimeException("username");

    }
}

