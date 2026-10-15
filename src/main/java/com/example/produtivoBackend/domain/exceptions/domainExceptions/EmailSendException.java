package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EmailSendException extends GlobalErrorExceptionsHandlers {
    public EmailSendException(){
        super(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Envio de email foi interrompido. Um erro inesperado ocorreu.");
    }

}
