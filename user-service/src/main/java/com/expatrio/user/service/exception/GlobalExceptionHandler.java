package com.expatrio.user.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		LoginResponse loginRes = new LoginResponse();
		loginRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		loginRes.setDescription(" Internal Server Error");
		loginRes.setErrorCode("500");
		String bodyOfResponse = loginRes.toString();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(UserNotFoundRuntimeException.class)
	public ResponseEntity<ErrorResponse> userHandleNotFound(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorCode("404");
		errors.setDescription("User not found");
		errors.setStatus(HttpStatus.NOT_FOUND);
		errors.setTimestamp(LocalDateTime.now()); 
		
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidCredentialsException.class })
	public ResponseEntity<Object> handleInvalidCredentialsException(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorCode(String.valueOf(HttpStatus.UNAUTHORIZED));
		errors.setDescription("User or Password are incorrect!");
		errors.setStatus(HttpStatus.UNAUTHORIZED);
		errors.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ UserForbidden.class })
	public ResponseEntity<Object> userForbidden(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN));
		errors.setDescription("User do not have valid permissions");
		errors.setStatus(HttpStatus.FORBIDDEN);
		errors.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
	}
}
