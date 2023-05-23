package com.gavoyage.exception.exception;

public class ExpiredJwtException extends RuntimeException {

	public ExpiredJwtException() {
		super("유효하지 않은 토큰입니다.");
	}

	public ExpiredJwtException(String message) {
		super(message);
	}
	
}
