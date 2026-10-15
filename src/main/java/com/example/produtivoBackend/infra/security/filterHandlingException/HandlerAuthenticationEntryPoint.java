package com.example.produtivoBackend.infra.security.filterHandlingException;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.ErrorHandlingAuthenticationEntryPointDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class HandlerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private FilterApiError apiError;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        apiError.write(request,
                       response,
                       HttpStatus.UNAUTHORIZED,
                       authException,
               "Autenticação necessária ou token inválido.");

    }
}
