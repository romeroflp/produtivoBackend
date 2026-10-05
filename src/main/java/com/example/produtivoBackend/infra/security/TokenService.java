package com.example.produtivoBackend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.ExpiredOrInvalidTokenException;
import com.example.produtivoBackend.domain.users.entities.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    protected String secretKey;

    public String generateToken(Users user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("produtivo-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genInstantExpiresToken())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token de autorização", e);
        }
    }

    public String validateToken(String token){
          try{
              Algorithm algorithm = Algorithm.HMAC256(secretKey);
              return JWT.require(algorithm)
                      .withIssuer("produtivo-api")
                      .build()
                      .verify(token)
                      .getSubject();
          }catch (JWTVerificationException e){
              throw new ExpiredOrInvalidTokenException();
          }
    }

    private Instant genInstantExpiresToken(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }

}

