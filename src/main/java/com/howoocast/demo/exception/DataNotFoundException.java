package com.howoocast.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class DataNotFoundException extends RuntimeException {
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> DataNotFoundException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("DataNotFoundException message", e.getMessage());
        return map;
    }
}