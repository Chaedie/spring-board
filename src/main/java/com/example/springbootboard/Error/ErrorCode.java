package com.example.springbootboard.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400, "MEMBER-ERR-400", "EMAIL DUPLICATED"),
    AWS_CREDENTIALS_FAIL(400, "AWS-ERR-400", "AWS CREDENTIALS FAIL"),
    ;

    private int status;
    private String errorCode;
    private String message;
}
