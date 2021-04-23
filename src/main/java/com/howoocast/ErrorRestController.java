package com.howoocast;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.UniqueViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice("com.howoocast")
@Slf4j
public class ErrorRestController {

	@ExceptionHandler(UniqueViolationException.class)
	public ResponseEntity<?> uniqueViolationException(UniqueViolationException e) {
		log.debug("[Error Rest Controller] {}", e.getClass().getSimpleName());
		return e.getResponse();
	}

	@ExceptionHandler(EmptyValueException.class)
	public ResponseEntity<?> emptyValueException(EmptyValueException e) {
		log.debug("[Error Rest Controller] {}", e.getClass().getSimpleName());
		return e.getResponse();
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> dataNotFoundException(DataNotFoundException e) {
		log.debug("[Error Rest Controller] {}", e.getClass().getSimpleName());
		return e.getResponse();
	}
}
