package com.upc.pe.backend.shared.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    private static final String ROOT = "/";
    private static final String ERROR = "/error";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";
    private static final String OPEN_API = "/v3/api-docs/**";
    private static final String AUTH = "/api/v1/authentication/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(
                ROOT,
                ERROR,
                SWAGGER_UI,
                SWAGGER_HTML,
                OPEN_API,
                AUTH
        ).permitAll().anyRequest().authenticated());

        return http.build();

    }
}