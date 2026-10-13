package com.example.produtivoBackend.infra.security.exception;

import java.time.LocalDateTime;

public record ErrorHandlingAuthenticationEntryPointDTO(LocalDateTime localDateTime, int status, String error, String message, String path) {
    public ErrorHandlingAuthenticationEntryPointDTO(int status, String error, String message, String path){
        this(LocalDateTime.now(), status ,error, message, path);
    }
}
