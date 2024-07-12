package com.cagri.studentapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNumberDuplicateException.class)
    public ResponseEntity<ApiExceptionResponse> handleException(StudentNumberDuplicateException exception) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> handleException(ApiException apiException) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(apiException.getHttpStatus().value(), apiException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiExceptionResponse, apiException.getHttpStatus());

    }


    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> handleException(HandlerMethodValidationException exception) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                Arrays.stream(Objects.requireNonNull(exception.getDetailMessageArguments())).findFirst().isPresent()
                        ? Arrays.stream(exception.getDetailMessageArguments()).findFirst().get().toString() : "exception", LocalDateTime.now());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> handleException(Exception exception) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
