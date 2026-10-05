package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.users.DTOs.RequestCredentialsUser;
import com.example.produtivoBackend.domain.users.services.AccountService;
import com.example.produtivoBackend.domain.users.services.EmailService;
import com.example.produtivoBackend.domain.users.DTOs.EmailSendResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgotPassword")
    public ResponseEntity<EmailSendResponseDTO> sendEmailForRedefinePassword(@RequestBody RequestCredentialsUser infoUser){

        return ResponseEntity.ok(emailService.sendEmailForRedefinePassword(infoUser,
                "Redefinir senha"));

    }





}
