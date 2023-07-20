package com.example.springbootboard.global.error.Exception;

import com.example.springbootboard.global.error.errorcode.CommonErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppException extends RuntimeException {
    private CommonErrorCode commonErrorCode;

    public AppException(CommonErrorCode commonErrorCode, String message) {
        super(message);
        this.commonErrorCode = commonErrorCode;
    }
}
