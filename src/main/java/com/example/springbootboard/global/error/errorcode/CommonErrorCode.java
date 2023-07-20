package com.example.springbootboard.global.error.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    ITEM_NOT_FOUND(404, "COMMON-ERR-404", "ITEM NOT FOUND"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400, "MEMBER-ERR-400", "EMAIL DUPLICATED"),
    VALIDATION_ERROR(400, "VALIDATION-ERR-400", "VALIDATION ERROR"),
    AWS_CREDENTIALS_FAIL(400, "AWS-ERR-400", "AWS CREDENTIALS FAIL"),
    AUTHORIZATION_FAIL(403, "AUTH-ERR-403", "CHECK ID PASSWORD"),
    ;

    private int httpStatus;
    private String errorCode;
    private String message;

}
