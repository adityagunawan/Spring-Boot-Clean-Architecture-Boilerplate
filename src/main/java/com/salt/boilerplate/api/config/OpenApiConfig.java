package com.salt.boilerplate.api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI boilerplateOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Boilerplate API")
                        .description("API documentation for Spring Boot Boilerplate")
                        .version("v1")
                        .contact(new Contact().name("SALT").email("info@example.com"))
                        .license(new License().name("Unspecified")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://example.com"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.salt.boilerplate.api")
                .build();
    }
}
