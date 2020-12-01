package com.expatrio.auth.beans;

public class AuthResponse {
    private String jwt;
    private AuthRequest user;

    public AuthResponse(String jwt, AuthRequest user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthRequest getUser() {
        return user;
    }

    public void setUser(AuthRequest user) {
        this.user = user;
    }
}
