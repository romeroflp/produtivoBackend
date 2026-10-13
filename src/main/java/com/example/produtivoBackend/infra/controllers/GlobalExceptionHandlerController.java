package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import com.example.produtivoBackend.domain.exceptions.GlobalErrorResponse;
import com.example.produtivoBackend.infra.security.exception.ErrorHandlingAuthenticationEntryPointDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandlingAuthenticationEntryPointDTO>  errorEntryPoint(
            MethodArgumentNotValidException ex, HttpServletRequest request){

        String fieldError = ex.getBindingResult().getFieldErrors().get(0).getField();
        String messageError = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String completeErrorMessage = "Error: " + fieldError +" : "+ messageError;

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorHandlingAuthenticationEntryPointDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        completeErrorMessage,
                        request.getRequestURI()
                )
        );



    }

}
