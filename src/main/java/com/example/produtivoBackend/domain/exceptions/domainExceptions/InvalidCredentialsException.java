package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidCredentialsException extends GlobalErrorExceptionsHandlers {
    public InvalidCredentialsException(String message) {
        super(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, message);
    }
}
