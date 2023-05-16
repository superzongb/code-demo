package com.hakunamatata.demo.adapter.driving.rest;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<?> argumentsValidateExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
