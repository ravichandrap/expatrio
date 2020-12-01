package com.gateway.gateway.service;

import com.gateway.gateway.beans.AuthTokenModel;
import org.springframework.web.bind.annotation.RequestHeader;


public interface AuthService {

    public AuthTokenModel getJWTToken(@RequestHeader("apikey") String apiKey);

}