package com.expatrio.auth.beans;

public class AuthResponse {
    private String authorization;
    private UserDetails user;

    public AuthResponse(String authorization, UserDetails user) {
        this.authorization = authorization;
        this.user = user;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
