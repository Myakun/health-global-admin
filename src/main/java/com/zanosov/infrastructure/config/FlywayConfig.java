package com.zanosov.infrastructure.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(
            DataSource dataSource,
            @Value("${spring.flyway.locations:classpath:db/migration}") String[] locations,
            @Value("${spring.flyway.clean-disabled:true}") boolean cleanDisabled,
            @Value("${spring.flyway.clean-on-validation-error:false}") boolean cleanOnValidationError) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .cleanDisabled(cleanDisabled)
                .cleanOnValidationError(cleanOnValidationError)
                .load();
    }
}
