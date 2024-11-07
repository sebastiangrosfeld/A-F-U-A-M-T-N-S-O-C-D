package com.study.carDealershipsServer.common.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
class GlobalExceptionHandler {

    private static final String ERROR_KEY = "error";

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, String>> handleServiceException(ServiceException e) {
        return createResponse(e.getMessage(), e.getStatus());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, String>> handleException(RuntimeException e) {
//        return createResponse(e.getMessage(), BAD_REQUEST);
//    }

    private ResponseEntity<Map<String, String>> createResponse(String error, HttpStatus status) {
        final var content = Map.of(ERROR_KEY, error);
        return new ResponseEntity<>(content, new HttpHeaders(), status);
    }
}
