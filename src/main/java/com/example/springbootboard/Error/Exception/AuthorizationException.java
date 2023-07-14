package com.example.springbootboard.Error.Exception;

import com.example.springbootboard.Error.ErrorCode;

public class AuthorizationException extends RuntimeException {
    private ErrorCode errorCode;

    public AuthorizationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
