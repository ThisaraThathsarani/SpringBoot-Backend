package com.example.springbootTodo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends  RuntimeException{

    /**
     * HttpStatus of the error
     */
    private final HttpStatus httpStatus;


    public BadRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
