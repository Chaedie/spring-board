package com.example.springbootboard.global.error.errorcode;

public interface ErrorCode {
    String getErrorCode();

    int getHttpStatus();

    String getMessage();
}
