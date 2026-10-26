package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class InvalidCategoryOrNotExistException extends GlobalErrorExceptionsHandlers {
    public InvalidCategoryOrNotExistException() {
        super(LocalDateTime.now(), HttpStatus.NOT_FOUND, "A categoria informada NÃO foi encontrada. ");
    }
    public InvalidCategoryOrNotExistException(String message){
        super(LocalDateTime.now(), HttpStatus.NOT_FOUND, message);
    }
}
