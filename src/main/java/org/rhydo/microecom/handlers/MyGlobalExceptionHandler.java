package org.rhydo.microecom.handlers;

import org.rhydo.microecom.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException e) {
       Map<String, String> error = new HashMap<>();
       error.put("message", e.getMessage());
       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
