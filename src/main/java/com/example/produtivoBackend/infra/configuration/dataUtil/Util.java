package com.example.produtivoBackend.infra.configuration.dataUtil;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.Random;

@Configuration
public class Util {
    @Bean
    public Flyway flyway(DataSource dataSource){
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();

        return flyway;
    }

    @Bean
    public SecureRandom instanceSecureRandom(){
        return new SecureRandom();
    }

}
