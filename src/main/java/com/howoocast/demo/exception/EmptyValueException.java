package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmptyValueException extends NullPointerException {
	
	public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("비정상적인 입력값입니다.", HttpStatus.FORBIDDEN);
	}

}
