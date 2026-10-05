package com.example.produtivoBackend.infra.security;

import com.example.produtivoBackend.domain.exceptions.GlobalErrorResponse;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.ExpiredOrInvalidTokenException;
import com.example.produtivoBackend.infra.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header != null){
            try {
                String token = header.substring("Bearer ".length());
                String email = tokenService.validateToken(token);
                UserDetails user = userRepo.findByEmail(email);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);

            }catch (ExpiredOrInvalidTokenException e){
                log.info("{}", e.getMessage(),e);
                throw new ExpiredOrInvalidTokenException("Token inválido. Não foi possível realizar a autenticação.");
            }
        }
        filterChain.doFilter(request, response);

    }
}


