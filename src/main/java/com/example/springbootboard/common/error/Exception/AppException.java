package com.example.springbootboard.common.error.Exception;

import com.example.springbootboard.common.error.errorcode.CommonErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppException extends RuntimeException {
    private CommonErrorCode commonErrorCode;

    public AppException(CommonErrorCode commonErrorCode, String message) {
        super(message);
        this.commonErrorCode = commonErrorCode;
    }
}
