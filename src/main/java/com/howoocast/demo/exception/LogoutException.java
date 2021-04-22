package com.howoocast.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LogoutException extends RuntimeException{
    public ResponseEntity<String> getResponse(){
        return new ResponseEntity<>("로그아웃 부탁드립니다.", HttpStatus.FORBIDDEN);
    }
}


