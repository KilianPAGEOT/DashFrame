package com.github.dashframe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String API_VERSION = "v1";
    public static final String API_BASE = "api/" + API_VERSION;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        WebMvcConfigurer.super.configurePathMatch(configurer);
        // Adds the API prefix to all routes located inside a REST controller
        configurer.addPathPrefix(API_BASE, HandlerTypePredicate.forAnnotation(RestController.class));
    }
}
