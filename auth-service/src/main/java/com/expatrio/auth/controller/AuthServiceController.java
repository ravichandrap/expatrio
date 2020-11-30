package com.expatrio.auth.controller;

import com.expatrio.auth.beans.AuthenticationResponse;
import com.expatrio.auth.beans.UserProfile;
import com.expatrio.auth.util.JwtUtil;
import com.expatrio.auth.util.WebClientAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthServiceController {

    static final Logger logger = LoggerFactory.getLogger(AuthServiceController.class);

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    WebClientAPI webClientAPI;

    @RequestMapping(value = "/loginone", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest user) throws Exception {
        logger.debug("AuthenticationRequest: {} ", user);
        UserProfile userProfile = null;
        try {
            userProfile = webClientAPI.getUserFromUserService(user.getUsername());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        final String jwt = jwtTokenUtil.generateToken(userProfile);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateLoginToken(@RequestHeader(name = "Authorization") String jwt,
                                                      @RequestBody ValidateUser ValidateUser) {
        return new ResponseEntity<>(jwtTokenUtil.validateToken(jwt, ValidateUser.getEmail()), HttpStatus.OK);
    }

}
class AuthRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class ValidateUser {
    private String jwt;
    private String email;

    public ValidateUser(String jwt, String email) {
        this.jwt = jwt;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}