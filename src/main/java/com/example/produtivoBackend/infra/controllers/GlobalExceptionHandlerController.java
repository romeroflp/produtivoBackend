package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import com.example.produtivoBackend.domain.exceptions.GlobalErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    public ResponseEntity<GlobalErrorResponse> errorResponse(HttpStatus status,String message){
        return ResponseEntity.status(status).body(new GlobalErrorResponse(LocalDateTime.now(), message));
    }

    @ExceptionHandler(GlobalErrorExceptionsHandlers.class)
    public ResponseEntity<GlobalErrorResponse> errorHandler(GlobalErrorExceptionsHandlers err){
        log.info("{}", err.getMessage(),err);
        return errorResponse(err.getStatus(), err.getMessage());

    }

}
