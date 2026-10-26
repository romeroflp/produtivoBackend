package com.example.produtivoBackend.infra.controllers;

import com.example.produtivoBackend.domain.users.DTOs.*;
import com.example.produtivoBackend.domain.users.entities.Users;
import com.example.produtivoBackend.domain.users.services.AccountService;
import com.example.produtivoBackend.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountService accountService;

    @PostMapping("auth/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Valid AuthenticationUserRequestDTO auth){
        UsernamePasswordAuthenticationToken authToken = this.accountService.validateCredentials(auth);

        Authentication authentication = this.authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((Users) Objects.requireNonNull(authentication.getPrincipal(),
                "Nenhum token foi gerado. Verifique o método generateToken para compreender o erro."));

        return ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponseDTO(token));
    }

    @PostMapping("auth/register")
    public ResponseEntity<AuthenticationUserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        AuthenticationUserResponseDTO userResponse = accountService.registerUser(userRequestDTO);
        UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(userRequestDTO.email(), userRequestDTO.password());
        Authentication auth = this.authenticationManager.authenticate(data);
        String token = tokenService.generateToken((Users) Objects.requireNonNull(auth.getPrincipal(),
                "Nenhum token foi gerado. Verifique o método generateToken para compreender o erro." ));
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationUserResponseDTO(userResponse,token));

    }

    @PostMapping("resetPassword/validate")
        public ResponseEntity<Boolean> validatingResetPassword(@RequestBody RequestResetPasswordValidateCode codeProvideByUser){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.validatingConfirmationCodeForResetPassword(codeProvideByUser));
    }

    @PostMapping("resetPassword/confirm")
    public ResponseEntity<String> ResetPassword(@RequestBody RequestResetPasswordConfirm credentials){
        accountService.resetPasswordConfirm(credentials);
        return ResponseEntity.status(HttpStatus.OK).body("Nova senha atualizada com sucesso!");
    }


    //Criar Role de Admin para uso
    @GetMapping("users/allUsers")
    public ResponseEntity<Page<AuthenticationUserResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll(pageable));

    }

    @DeleteMapping("users/deleteUser")
    public ResponseEntity<Void> deleteUser(Authentication token){
        String email = token.getName();
        accountService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("users/deleteUser/{categoryId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id){
        accountService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("users/updateName")
    public ResponseEntity<UserResponseDTO> updateNameUser(Authentication email, @Valid String newName){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateNameUser(email, newName));

    }
}
