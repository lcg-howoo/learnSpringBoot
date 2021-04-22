package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongAccessException extends NullPointerException {

	private String message;

	public WrongAccessException() {
		this.message = "접근권한이 필요합니다.";
	}

	public WrongAccessException(String message) {
		this.message = message;
	}
    public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>(this.message, HttpStatus.FORBIDDEN);
	}
}
