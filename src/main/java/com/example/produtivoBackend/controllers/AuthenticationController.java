package com.example.produtivoBackend.controllers;

import com.example.produtivoBackend.domain.users.DTOs.*;
import com.example.produtivoBackend.domain.users.entity.Users;
import com.example.produtivoBackend.domain.users.userService.AuthenticationService;
import com.example.produtivoBackend.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticationService authenticationService;
    @Autowired
    public TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@RequestBody @Valid AuthenticationUserDTO auth){
        UsernamePasswordAuthenticationToken password = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
        Authentication authentication = this.authenticationManager.authenticate(password);

        String token = tokenService.generateToken((Users) Objects.requireNonNull(authentication.getPrincipal(),
                "Nenhum token foi gerado. Verifique o método generateToken para compreender o erro."));

        //Escrever sobre o que é o authenticationManager e ver o que o UsernamePasswordAuthenticationToken retorna.
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserLoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authenticationService.registerUser(userRequestDTO)
        );
    }




}
