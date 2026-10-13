package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import com.example.produtivoBackend.domain.tasks.entity.Category;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CategoryNotExistException extends GlobalErrorExceptionsHandlers {
    public CategoryNotExistException() {
        super(LocalDateTime.now(), HttpStatus.NOT_FOUND, "A categoria informada NÃO foi encontrada. ");
    }
    public CategoryNotExistException(String message){
        super(LocalDateTime.now(), HttpStatus.NOT_FOUND, message);
    }
}
