package com.example.springbootboard.Error.Exception;

import com.example.springbootboard.Error.errorcode.CommonErrorCode;
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
