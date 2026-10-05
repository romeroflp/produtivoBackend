package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidNewPasswordConfirmation extends GlobalErrorExceptionsHandlers {
    public InvalidNewPasswordConfirmation(String message) {
        super(LocalDateTime.now(), HttpStatus.BAD_REQUEST, message);
    }
}
