package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrongLoginException extends NullPointerException{
    public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("이미 로그인 됐습니다.", HttpStatus.FORBIDDEN);
	}
}
