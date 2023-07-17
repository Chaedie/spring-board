package com.example.springbootboard.Error.errorcode;

public interface ErrorCode {
    String getErrorCode();

    int getHttpStatus();

    String getMessage();
}
