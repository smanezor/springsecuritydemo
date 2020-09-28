package com.test.springsecuritydemo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtAuthException extends RuntimeException {

    private HttpStatus httpStatus;

    public JwtAuthException(String message) {
        super(message);
    }
}
