package com.upc.pe.backend.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for OpenAPI documentation.
 *
 * Defines API metadata, security schemes, and external documentation references
 * for the AutoMatch Platform.
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Configures the OpenAPI specification for the AutoMatch Platform.
     *
     * @return the customized {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI AutoMatchPlatformOpenApi() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("AutoMatch Platform API")
                        .description("Documentation for the AutoMatch Platform API")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("AutoMatch Platform Wiki Documentation")
                        .url("https://github.com/1ASI0729-2510-4289-G3-KeepTeam"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT Bearer token **_only_**")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}