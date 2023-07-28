package com.example.springbootboard.common.error.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PostErrorCode implements ErrorCode {
    ITEM_NOT_FOUND(404, "POST-ERR-404", "조건에 맞는 포스트가 없습니다."),
    AUTHORIZATION_FAIL(403, "AUTH-ERR-403", "아이디 비밀번호를 확인해주세요."),
    ;


    private int httpStatus;
    private String errorCode;
    private String message;
}
