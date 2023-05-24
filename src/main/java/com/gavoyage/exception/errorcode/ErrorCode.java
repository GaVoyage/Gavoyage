package com.gavoyage.exception.errorcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    REQUEST_NOT_ALLOWED(HttpStatus.NOT_FOUND, "Request is not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    
    // Jwt
    EXPIRED_JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "Access Token is expired"),
    INVALID_JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "Invalid access token");
	
    private final HttpStatus httpStatus;
    private final String message;

}

