package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmptyValueException extends NullPointerException {

	private String parameterName;

	public EmptyValueException(String parameterName) {
		this.parameterName = parameterName;
	}
	
	public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>(parameterName + " 항목값이 비정상적입니다.", HttpStatus.FORBIDDEN);
	}

}
