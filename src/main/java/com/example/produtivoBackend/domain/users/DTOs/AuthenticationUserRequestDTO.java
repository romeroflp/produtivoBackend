package com.example.produtivoBackend.domain.users.DTOs;

public record AuthenticationUserRequestDTO(String email, String password) {
}
//Colocar anotações de validação