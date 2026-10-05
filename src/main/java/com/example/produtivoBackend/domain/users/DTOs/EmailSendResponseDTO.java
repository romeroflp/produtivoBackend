package com.example.produtivoBackend.domain.users.DTOs;

import lombok.Data;

@Data
public class EmailSendResponseDTO{

    private String message;
    private String messageId;
    private String confirmationCode;
    private String token;


}
