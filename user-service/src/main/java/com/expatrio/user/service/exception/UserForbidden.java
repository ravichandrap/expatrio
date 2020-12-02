package com.expatrio.user.service.exception;

public class UserForbidden extends RuntimeException{
    UserForbidden(String message) {
        super(message);
    }
}
