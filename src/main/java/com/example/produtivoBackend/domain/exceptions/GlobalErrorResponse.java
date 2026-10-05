package com.example.produtivoBackend.domain.exceptions;

import java.time.LocalDateTime;

public record GlobalErrorResponse(LocalDateTime instant, String message) {
}
