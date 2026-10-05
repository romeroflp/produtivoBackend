package com.example.produtivoBackend.domain.users.DTOs;

import com.example.produtivoBackend.domain.users.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthenticationUserResponseDTO(UUID id, String name, String email, String token, @JsonIgnore LocalDateTime createdIn) {


    public AuthenticationUserResponseDTO(Users user) {
        this(user.getId(), user.getName(), user.getEmail(), null, user.getCreatedIn());
    }

    public AuthenticationUserResponseDTO(AuthenticationUserResponseDTO user, String token) {
        this(user.id(), user.name(), user.email(), token, null);
    }

}