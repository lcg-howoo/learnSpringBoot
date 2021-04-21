package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongSessionException extends RuntimeException {
    public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("세션이 없습니다.", HttpStatus.FORBIDDEN);
	}
}
