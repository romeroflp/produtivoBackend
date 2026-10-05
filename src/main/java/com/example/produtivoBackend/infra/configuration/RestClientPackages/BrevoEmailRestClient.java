package com.example.produtivoBackend.infra.configuration.RestClientPackages;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@ConfigurationProperties(prefix="brevo.header")
@RequiredArgsConstructor
public class BrevoEmailRestClient {

    private final String apiKey;
    private final String baseUrl;

    @Bean
    public RestClient restClientEmail(){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("api-key", apiKey)
                .defaultHeader("accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();

    }
}
