package com.example.springbootboard.global.error.Exception;

import com.example.springbootboard.global.error.errorcode.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 조건에 맞는 데이터가 DB에 없을 경우 던지는 예외
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ItemNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public ItemNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ItemNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
