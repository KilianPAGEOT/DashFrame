package com.github.dashframe.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.github.dashframe.RFC3339DateFormat;
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
        var mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.setDateFormat(new RFC3339DateFormat());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        return mapper;
    }
}
