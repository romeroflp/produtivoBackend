package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExpiredOrInvalidTokenException extends GlobalErrorExceptionsHandlers {

    public ExpiredOrInvalidTokenException() {
        super(LocalDateTime.now(), HttpStatus.FORBIDDEN, "A sessão expirou por inatividade.");

    }

    public ExpiredOrInvalidTokenException(String message) {
        super(LocalDateTime.now(), HttpStatus.UNAUTHORIZED, message);
    }
}

