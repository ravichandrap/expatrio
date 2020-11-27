package com.expatrio.api.gateway.controller;


import com.expatrio.api.gateway.entity.AuthenticationResponse;
import com.expatrio.api.gateway.entity.User;
import com.expatrio.api.gateway.exception.InvalidCredentialsException;
import com.expatrio.api.gateway.security.CustomUserDetailsService;
import com.expatrio.api.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAuthController {
    static final Logger logger = LoggerFactory.getLogger(LoginAuthController.class);

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user)
            throws Exception {
        logger.debug("AuthenticationRequest: {} ", user.getEmail());
        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsernameAndPwd(user.getEmail(), user.getPassword());
            logger.debug(userDetails.toString());
        } catch (NullPointerException ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}