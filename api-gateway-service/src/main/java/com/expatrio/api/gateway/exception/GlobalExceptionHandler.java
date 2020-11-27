package com.expatrio.api.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundRuntimeException.class)
    public ResponseEntity<ErrorResponse> userHandleNotFound(Exception ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.setErrorCode("404");
        errors.setDescription("User not found");
        errors.setStatus(HttpStatus.NOT_FOUND);
        errors.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ BadCredentialsException.class, IllegalArgumentException.class })
    public ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {
        ErrorResponse errors = new ErrorResponse();
        errors.setErrorCode("404");
        errors.setDescription("User not found");
        errors.setStatus(HttpStatus.NOT_FOUND);
        errors.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
