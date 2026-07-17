package com.example.produtivoBackend.domain.users.DTOs;

import com.example.produtivoBackend.domain.users.Users;

import java.util.UUID;

public record UserResponseDTO(UUID id, String email, String password) {

    public UserResponseDTO (Users user){
        this(user.getId(), user.getEmail(), user.getPassword());
    }
}
