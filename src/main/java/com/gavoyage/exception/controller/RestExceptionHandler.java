package com.gavoyage.exception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gavoyage.exception.RestException;
import com.gavoyage.exception.errorcode.ErrorCode;
import com.gavoyage.exception.response.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RestException.class)
	protected ResponseEntity<ErrorResponse> handleRestException(RestException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return ResponseEntity
				.status(errorCode.getHttpStatus().value())
				.body(new ErrorResponse(errorCode));
	}
	
}
