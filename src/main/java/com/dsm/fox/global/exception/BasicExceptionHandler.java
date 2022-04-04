package com.dsm.fox.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BasicExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ExceptionRs> basicExceptionHandler(final BasicException e) {
        final String message = e.getMessage();
        final int code = e.getCode();
        return new ResponseEntity<>(new ExceptionRs(code, message),HttpStatus.valueOf(code));
    }
}
