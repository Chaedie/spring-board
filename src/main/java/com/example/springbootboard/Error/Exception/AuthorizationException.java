package com.example.springbootboard.Error.Exception;

import com.example.springbootboard.Error.errorcode.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
public class AuthorizationException extends RuntimeException {

    private ErrorCode errorCode;

    public AuthorizationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public AuthorizationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
