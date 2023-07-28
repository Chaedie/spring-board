package com.example.springbootboard.common.error.handler;

import com.example.springbootboard.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<CommonResponse<Object>> globalErrorHandle(Exception e) {
        log.error("[{}] handled : {} ", e.getClass().getSimpleName(), e.getMessage());
        String errorMessage = e.getMessage();
        return CommonResponse.badRequest(errorMessage != null ? errorMessage : "entity not found exception");
    }
}
