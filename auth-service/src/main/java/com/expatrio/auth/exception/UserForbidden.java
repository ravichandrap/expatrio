package com.expatrio.auth.exception;

public class UserForbidden extends RuntimeException{
    public UserForbidden(String message) {
        super(message);
    }
}
