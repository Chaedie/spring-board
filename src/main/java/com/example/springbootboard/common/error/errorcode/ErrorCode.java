package com.example.springbootboard.common.error.errorcode;

public interface ErrorCode {
    String getErrorCode();

    int getHttpStatus();

    String getMessage();
}
