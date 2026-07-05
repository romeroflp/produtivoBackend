package com.example.produtivoBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutivoBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProdutivoBackendApplication.class, args);
	}

}

//Enum faz validação automatica, porem é sensitiveCase? E caso o cliente
//enviar em minusculo?
//Usar bean validations
//Relembrar programação orientada a interfaces