package com.example.produtivoBackend.domain.users.DTOs;

public record RequestCredentialsUser(String name, String recipientEmail) {
 //Pegar o nome pelo email registrado ao inves de ser passado pelo cliente
}
