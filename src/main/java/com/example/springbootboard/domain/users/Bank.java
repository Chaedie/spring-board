package com.example.springbootboard.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Bank {
    TOSS("BANK_TOSS", "토스뱅크"),
    KAKAO("BANK_KAKAO", "카카오뱅크"),
    KB("BANK_KB", "국민은행"),
    ;

    private final String key;
    private final String title;
}
