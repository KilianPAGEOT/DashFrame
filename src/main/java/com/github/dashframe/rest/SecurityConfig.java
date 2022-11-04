package com.github.dashframe.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5174")
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/**").authorizeRequests()
                .antMatchers(new String[]{"/*"}).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().and()
                .oauth2Login().defaultSuccessUrl("/api/v1/user", true);


        return httpSecurity.build();
    }

    /*@Override
    public void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**").authorizeRequests()
                .antMatchers(new String[]{"/", "/not-restricted"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }*/
}