package com.company.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> Exceptionhandler(Exception ex, WebRequest wr){
        ResponseException re = new ResponseException(LocalDateTime.now().toString(), ex.getMessage(), wr.getDescription(false));

        return new ResponseEntity<>(re, HttpStatus.BAD_REQUEST);
    }

}
