package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class UserAlreadyExistOrInvalidException extends GlobalErrorExceptionsHandlers{
    public UserAlreadyExistOrInvalidException() {
        super(LocalDateTime.now(), HttpStatus.CONFLICT,
                "Os dados do usuário informado já foram cadastrados. Não foi possível cadastrar um novo usuário.");
    }
    public UserAlreadyExistOrInvalidException(String message) {
        super(null, null, message);
    }
}
