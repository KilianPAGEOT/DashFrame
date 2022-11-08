package com.github.dashframe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * Configures the global JSON {@link ObjectMapper} instance.
 * <p>
 * Beans!
 */
@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
