package com.gavoyage.exception;

import com.gavoyage.exception.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestException extends RuntimeException {
    private final ErrorCode errorCode;
}
