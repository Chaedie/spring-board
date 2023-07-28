package com.example.springbootboard.common;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@Builder
public class ResponseDTO<T> {
    private final Integer code;
    private final String message;
    private final T data;
}
