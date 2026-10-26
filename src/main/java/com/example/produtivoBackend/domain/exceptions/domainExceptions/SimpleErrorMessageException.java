package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class SimpleErrorMessageException extends GlobalErrorExceptionsHandlers {
    public SimpleErrorMessageException(String message) {
        super(LocalDateTime.now(), HttpStatus.BAD_REQUEST ,message);
    }

    public SimpleErrorMessageException(String message, HttpStatus status){
        super(LocalDateTime.now(), status, message);
    }

}
