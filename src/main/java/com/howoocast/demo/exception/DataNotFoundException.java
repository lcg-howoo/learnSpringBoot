package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataNotFoundException extends NullPointerException {

	public ResponseEntity<String> getResponse() {
		return new ResponseEntity<>("해당 아이디의 데이터를 찾을 수 없습니다.", HttpStatus.FORBIDDEN);
	}
}