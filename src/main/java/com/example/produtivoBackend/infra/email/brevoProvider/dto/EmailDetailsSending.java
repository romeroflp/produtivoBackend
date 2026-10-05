package com.example.produtivoBackend.infra.email.brevoProvider.dto;

import java.util.List;

public record EmailDetailsSending(Sender sender, List<To> to, String subject, String htmlContent) {
    public record Sender(String name, String email){}
    public record To(String name, String email){}

    public EmailDetailsSending(String recipientName, String recipientEmail, String subject,String htmlContent){
        this(new Sender("Produtivo", "felipermrcosta@gmail.com"),
                List.of(new To(recipientName, recipientEmail)),subject ,htmlContent);
    }

}
