package com.test.springsecuritydemo.exception.advice;

import com.test.springsecuritydemo.exception.JwtAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<Object> handleJwtAuthToken(JwtAuthException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", "Authentication failed");
        body.put("errors", e.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
