package com.example.produtivoBackend.domain.users.DTOs;

import com.example.produtivoBackend.domain.users.entities.Users;

public record UserResponseDTO(String name, String email) {
    public UserResponseDTO(Users user){
        this(user.getName(), user.getEmail());
    }

}
