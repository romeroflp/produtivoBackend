package com.example.produtivoBackend.service;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.InvalidCredentialsException;
import com.example.produtivoBackend.domain.users.DTOs.AuthenticationUserRequestDTO;
import com.example.produtivoBackend.domain.users.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private AccountService authService;
    @Mock
    private AuthenticationManager manager;


    @Test
    public void InvalidInputEmail(){
        AuthenticationUserRequestDTO userDTO = new AuthenticationUserRequestDTO("felipe.gmail.com", "1234");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password());
        when(manager.authenticate(authToken)).thenThrow(new InvalidCredentialsException("Dados inválidos. Verifique se o email e senha foram digitados corretamente."));

        Assertions.assertThrows(InvalidCredentialsException.class, () -> authService.loadUserByUsername(userDTO.email()));

    }



}
