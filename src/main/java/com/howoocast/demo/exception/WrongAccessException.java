package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongAccessException extends NullPointerException {
    public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("접근권한이 필요합니다.", HttpStatus.FORBIDDEN);
	}
}
