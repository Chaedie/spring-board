package com.example.springbootboard.global.error.handler;

import com.example.springbootboard.global.error.ErrorResponse;
import com.example.springbootboard.global.error.Exception.AuthorizationException;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.global.error.errorcode.CommonErrorCode;
import com.example.springbootboard.global.error.errorcode.ErrorCode;
import com.example.springbootboard.global.error.errorcode.PostErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(JsonProcessingException.class)
    protected String handleJsonParseException(JsonProcessingException ex, Model model) {
        log.error("handleAuthorizationException  ", ex);
        ErrorCode errorCode = CommonErrorCode.JSON_PARSE_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();

        model.addAttribute(errorResponse);

        return "errors/error";
    }

    @ExceptionHandler(AuthorizationException.class)
    protected String handleAuthorizationException(AuthorizationException ex, Model model) {
        log.error("handleAuthorizationException  ", ex);
        ErrorCode errorCode = PostErrorCode.AUTHORIZATION_FAIL;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();

        model.addAttribute(errorResponse);

        return "errors/error";
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected String handleItemNotFoundException(ItemNotFoundException ex, Model model) {
        log.error("handleItemNotFoundException  ", ex);
        ErrorCode errorCode = PostErrorCode.ITEM_NOT_FOUND;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();

        model.addAttribute(errorResponse);

        return "errors/error";
    }

    /**
     * Handle item not found exception rest response entity.
     * Rest 방식의 경우 아래 예제코드를 사용하면 된다.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleEntityNotFoundExceptionRest(EntityNotFoundException ex) {
        log.error("handleEntityNotFoundException", ex);
        ErrorCode errorCode = PostErrorCode.ITEM_NOT_FOUND;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected String handleException(Exception ex, Model model) {
        log.error("handleException  ", ex);
        ErrorCode errorCode = CommonErrorCode.INTER_SERVER_ERROR;
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(errorCode.getHttpStatus())
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();

        model.addAttribute(errorResponse);

        return "errors/error";
    }
}
