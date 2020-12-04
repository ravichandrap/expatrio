package com.expatrio.user.service.beans;

public class UserProfile {
    private String email;
    private String password;

    public UserProfile(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
