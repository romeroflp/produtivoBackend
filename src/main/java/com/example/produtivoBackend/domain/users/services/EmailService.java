package com.example.produtivoBackend.domain.users.services;

import com.example.produtivoBackend.domain.users.DTOs.RequestCredentialsUser;
import com.example.produtivoBackend.domain.users.DTOs.EmailSendResponseDTO;


public interface EmailService{
    EmailSendResponseDTO sendEmailForRedefinePassword(RequestCredentialsUser infoUser, String subject);

}
