package com.example.produtivoBackend.controllers;

import com.example.produtivoBackend.domain.users.DTOs.AuthenticationUserDTO;
import com.example.produtivoBackend.domain.users.DTOs.UserRequestDTO;
import com.example.produtivoBackend.domain.users.DTOs.UserResponseDTO;
import com.example.produtivoBackend.domain.users.Users;
import com.example.produtivoBackend.domain.users.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationUserController {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationUserDTO auth){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
        this.authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.registerUser(userRequestDTO)
        );
    }

}
