package com.example.produtivoBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProdutivoBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProdutivoBackendApplication.class, args);
	}
}

// Coloco o properties do Brevo separado dos Beans??
// Sempre que eu quiser injetar uma dependencia, todas as intancias dentro da camada de configuração vão ser
// gerenciadas pelo container do spring. Se eu fizer isso varias vezes, tem algum impacto???