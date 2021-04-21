package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongPassowrdException extends RuntimeException{
    public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("비밀번호가 틀렸습니다.", HttpStatus.FORBIDDEN);
	}
}
