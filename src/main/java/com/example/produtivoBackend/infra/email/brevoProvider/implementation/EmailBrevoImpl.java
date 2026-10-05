package com.example.produtivoBackend.infra.email.brevoProvider.implementation;

import com.example.produtivoBackend.domain.exceptions.domainExceptions.EmailSendException;
import com.example.produtivoBackend.domain.exceptions.domainExceptions.EmailSendInvalidRoute;
import com.example.produtivoBackend.domain.users.DTOs.RequestCredentialsUser;
import com.example.produtivoBackend.domain.users.services.AccountService;
import com.example.produtivoBackend.domain.users.services.EmailService;
import com.example.produtivoBackend.infra.email.brevoProvider.dto.EmailDetailsSending;
import com.example.produtivoBackend.domain.users.DTOs.EmailSendResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Slf4j
@Service
public class EmailBrevoImpl implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RestClient restClient;

    @Autowired
    private AccountService accountService;

    @Override
    public EmailSendResponseDTO sendEmailForRedefinePassword(RequestCredentialsUser infoUser, String subject) {
        String confirmationCode = accountService.generateCodeConfirmPasswordReset();
        EmailDetailsSending details = createTemplateEmail(infoUser, subject, confirmationCode);

        EmailSendResponseDTO sendEmail;

        try {
            sendEmail = restClient.post()
                    .uri("/smtp/email")
                    .body(details)
                    .retrieve()
                    .body(EmailSendResponseDTO.class);

            if (sendEmail == null) {
                log.info("Resposta da requisição retornou Null.");
                throw new NullPointerException("O envio do email de redefinição retornou valor nulo. Confirme se o email foi enviado corretamente.");

            }
            sendEmail.setMessage("Email enviado para " + infoUser.name() + " no email: " + infoUser.recipientEmail());
            sendEmail.setConfirmationCode(confirmationCode);
            sendEmail.setToken(accountService.createResetToken(infoUser.recipientEmail()));

        }catch (EmailSendException e) {
            throw e;
        }catch (HttpClientErrorException e){
            log.warn("{}", e.getMessage(), e);
            throw new EmailSendInvalidRoute();

        }catch (RuntimeException e) {
            log.warn("{}", e.getMessage(), e);
            throw new EmailSendException();
        }

        return sendEmail;
    }


    private EmailDetailsSending createTemplateEmail(RequestCredentialsUser infoUser, String subject, String code){
        Context context = new Context();

        context.setVariable("name", infoUser.name());
        context.setVariable("code", code); // Objeto que armazena as variaveis do template html

        String html = templateEngine.process("emailTemplate", context); // Cria html com as variaveis setadas

        return new EmailDetailsSending(infoUser.name(), infoUser.recipientEmail(), subject, html);
    }




}
