package com.expatrio.auth.beans;

public class AuthenticationResponse {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
