package com.gateway.gateway.service;

import com.gateway.gateway.beans.AuthTokenModel;
import com.gateway.gateway.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


public interface AuthService {

    public AuthTokenModel getJWTToken(@RequestHeader("apikey") String apiKey);

}