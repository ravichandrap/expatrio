package com.expatrio.user.service.exception;

public class InvalidCredentialsException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidCredentialsException(String msg) {
        super("invalid credentials please try again"+msg);
    }

}
