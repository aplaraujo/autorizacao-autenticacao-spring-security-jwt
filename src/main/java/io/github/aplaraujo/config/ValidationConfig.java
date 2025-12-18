package io.github.aplaraujo.config;

import io.github.aplaraujo.services.models.validators.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }
}
