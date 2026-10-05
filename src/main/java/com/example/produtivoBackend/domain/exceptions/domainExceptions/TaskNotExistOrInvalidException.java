package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class TaskNotExistOrInvalidException extends GlobalErrorExceptionsHandlers {
    public TaskNotExistOrInvalidException() {

        super(LocalDateTime.now(), HttpStatus.NOT_FOUND, "Essa tarefa não existe. O valor inserido é inválido.");
    }
}
