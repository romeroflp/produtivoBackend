package com.example.produtivoBackend.infra.security;

import com.example.produtivoBackend.infra.security.exception.ErrorHandlingAuthenticationEntryPointDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorHandlingAuthenticationEntryPointDTO error = new ErrorHandlingAuthenticationEntryPointDTO(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Token inválido ou expirado. O usuário deve fazer login novamente.",
                authException.getMessage(),
                request.getServletPath()
        );

        mapper.writeValue(response.getOutputStream(), error);

    }
}
