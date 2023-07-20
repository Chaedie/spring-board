package com.example.springbootboard.global.error;

import com.example.springbootboard.global.error.errorcode.ErrorCode;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int httpStatus;
    private String message;
    private String errorCode;

    public ErrorResponse(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.getErrorCode();
    }
}
