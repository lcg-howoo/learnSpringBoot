package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UniqueViolationException extends NullPointerException {

	public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("해당 아이디는 이미 사용중입니다.", HttpStatus.FORBIDDEN);
	}
}
