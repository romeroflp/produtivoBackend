package com.example.produtivoBackend.domain.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public abstract class GlobalErrorExceptionsHandlers extends RuntimeException{
    private LocalDateTime instant;
    private HttpStatus status;
    private String message;
}
