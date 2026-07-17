package com.example.produtivoBackend.domain.users.DTOs;

import java.time.LocalDateTime;

public record UserRequestDTO(String name, String email, String password) {
}
