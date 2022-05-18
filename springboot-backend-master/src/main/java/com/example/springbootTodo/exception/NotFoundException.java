package com.example.springbootTodo.exception;

import org.springframework.http.HttpStatus;

/**
 * Customized Exception for Not Found.
 * Exception used when a resources not found in the system
 */
public class NotFoundException extends RuntimeException{

    /**
     * HttpStatus of the error
     */
    private final HttpStatus httpStatus;

    /**
     * Constructor Method.
     *
     * @param message : Exception Message to be set
     * @param httpStatus : The Http status assigned to thr Exception.
     */
    public NotFoundException(String message , HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}
