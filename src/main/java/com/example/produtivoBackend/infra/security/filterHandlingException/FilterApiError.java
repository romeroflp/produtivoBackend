package com.example.produtivoBackend.infra.security.filterHandlingException;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.ErrorHandlingAuthenticationEntryPointDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class FilterApiError {

    @Autowired
    private ObjectMapper mapper;

    public void write(HttpServletRequest request, HttpServletResponse response, HttpStatus status, RuntimeException ex, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());

        ErrorHandlingAuthenticationEntryPointDTO error = new ErrorHandlingAuthenticationEntryPointDTO(
                response.getStatus(),
                ex.getMessage(),
                message,
                request.getServletPath()
        );

        mapper.writeValue(response.getOutputStream(), error);

    }
}
