package com.expatrio.auth.beans;

public class AuthResponse {
    private String jwt;
    private UserDetails user;

    public AuthResponse(String jwt, UserDetails user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
