package com.example.produtivoBackend.domain.exceptions.domainExceptions;

import com.example.produtivoBackend.domain.exceptions.handlers.GlobalErrorExceptionsHandlers;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EmailSendInvalidRoute extends GlobalErrorExceptionsHandlers {
    public EmailSendInvalidRoute() {
        super(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR,
                "Endpoint de envío de email inválido. Verifique se a rota está correta.");
    }

    public EmailSendInvalidRoute(String message) {
        super(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, message);
    }


}
