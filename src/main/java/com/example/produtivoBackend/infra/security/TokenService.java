package com.example.produtivoBackend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.produtivoBackend.domain.users.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    public String validateToken(String token){ //Qual o retorno disso?
          try{
              Algorithm algorithm = Algorithm.HMAC256(secretKey);
              return JWT.require(algorithm)
                      .withIssuer("produtivo-api")
                      .build()
                      .verify(token)
                      .getSubject();
          }catch (JWTVerificationException e){
              throw new RuntimeException("Token inválido.", e);
          }
    }

    private Instant genInstantExpiresToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}

