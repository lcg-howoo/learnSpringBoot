package com.howoocast.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class UniqueViolationException extends RuntimeException {
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> UniqueViolationException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("UniqueViolationException message", e.getMessage());
        return map;
    }
}
