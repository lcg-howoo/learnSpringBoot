package com.howoocast.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class EmptyValueException extends RuntimeException {
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> EmptyValueException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("EmptyValueException message", e.getMessage());
        return map;
    }

}
