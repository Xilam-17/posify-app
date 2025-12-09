package com.posify.api.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> globalHandler(RuntimeException exception) {
    ErrorResponse response = new ErrorResponse();
    response.setMessage(exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
