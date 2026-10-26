package com.example.produtivoBackend.domain.users.services;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.*;
import com.example.produtivoBackend.domain.users.DTOs.*;
import com.example.produtivoBackend.domain.users.entities.ResetToken;
import com.example.produtivoBackend.domain.users.entities.Users;
import com.example.produtivoBackend.infra.repository.ResetTokenRepository;
import com.example.produtivoBackend.infra.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private SecureRandom secureRandom;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ResetTokenRepository tokenRepo;

    private String codeConfirmReset;
    private boolean isCorrectCode;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails userDetails = userRepo.findByEmail(email);
        if(userDetails == null){
            log.warn("Email fornecido pelo usuário inválido.");
            throw new InvalidCredentialsException("Dados inválidos. Verifique se o email e senha foram digitados corretamente.");
        }
        return userDetails;

    }

    public UsernamePasswordAuthenticationToken validateCredentials(AuthenticationUserRequestDTO auth){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
        UserDetails details = loadUserByUsername(auth.email());
        if (!encoder.matches(auth.password(), details.getPassword())) {
            log.warn("Senha fornecida pelo usuário inválido.");
            throw new InvalidCredentialsException("Dados inválidos. Verifique se o email e senha foram digitados corretamente.");
        }

        return authToken;
    }

    public AuthenticationUserResponseDTO registerUser(UserRequestDTO request){
        if(userRepo.findByEmail(request.email()) != null) {
            throw new UserAlreadyExistOrInvalidException();
        }
        Users newUser = new Users(request);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return new AuthenticationUserResponseDTO(userRepo.save(newUser));
    }

    public String createResetToken(String email){
        Users user = userRepo.findByEmail(email);
        ResetToken token = new ResetToken(UUID.randomUUID(), user.getId(),
                Instant.now().plus(5, ChronoUnit.MINUTES), false);
        tokenRepo.save(token);

        return token.getToken().toString();
    }

    public boolean resetTokenIsUsedOrExpired(String tokenResponse){
        ResetToken token = tokenRepo.findByToken(UUID.fromString(tokenResponse));
        if(token.isUsed() || token.getExpiredAt().isBefore(Instant.now())){
            tokenRepo.delete(token);
            throw new ExpiredOrInvalidTokenException("Token para reset de senha expirado ou inválido");
        }
        return false;
    }

    public boolean validatingConfirmationCodeForResetPassword(RequestResetPasswordValidateCode codeProvidedByUser){
        this.isCorrectCode = codeProvidedByUser.codeProvideByUser().equals(this.codeConfirmReset);
        log.error("{} - {}", this.codeConfirmReset, codeConfirmReset);
        return isCorrectCode;
    }

    public void resetPasswordConfirm(RequestResetPasswordConfirm credentials){ //retornar o token inves de email
        if(!this.isCorrectCode || resetTokenIsUsedOrExpired(credentials.resetToken())){
            throw new InvalidNewPasswordConfirmation("Código de confirmação por email incorreto. ");
        }
        if(!credentials.newPassword().equals(credentials.confirmNewPassword())){
            log.warn("Senhas não coincidem - senha: "+ credentials.newPassword()+ " - confirmação: "+ credentials.confirmNewPassword());
            throw new InvalidNewPasswordConfirmation("As senhas informadas não coincidem.");
        }

        UUID userId = tokenRepo.findByToken(UUID.fromString(credentials.resetToken())).getUserId();

        Users user = userRepo.findById(userId).orElseThrow(
                () -> new UserAlreadyExistOrInvalidException("Usuário não foi encontrado. Dados inválidos."));

        user.setPassword(this.encoder.encode(credentials.newPassword()));
        userRepo.save(user);

    }

    public String generateCodeConfirmPasswordReset(){
        this.codeConfirmReset = String.valueOf(this.secureRandom.nextInt(10000, 99999));
        return codeConfirmReset;
    }


    public Page<AuthenticationUserResponseDTO> findAll(Pageable pageable){
        Page<Users> users = userRepo.findAll(pageable);
        return new PageImpl<>(users.stream().map(AuthenticationUserResponseDTO::new).toList());

    }

    public UserResponseDTO updateNameUser(Authentication email,
                                          String newName){
        Users user = userRepo.findByEmail(email.getName());

        if(newName.length() <= 2 || newName.length() > 30 ||
           newName.matches("^[A-ZÀ-Úa-zà-ú][A-ZÀ-Úa-zà-ú]*(?:[ '-][A-ZÀ-Úa-zà-ú]+)*$") ||
           newName.isBlank()){
            throw new InvalidCredentialsException("O nome informado não é permitido. Tente novamente.");
        }
        user.setName(newName.trim());
        return new UserResponseDTO(userRepo.save(user));

    }

    public void deleteUser(String email){
        userRepo.delete(userRepo.findByEmail(email));
    }

    public void deleteUserById(UUID id){
        userRepo.deleteById(id);
    }



}
