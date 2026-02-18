package com.example.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {

    @Value("${swagger.app.uri}")
    public String directUrl;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    public String keycloakBaseUrl;

    @Bean
    public OpenAPI myOpenAPI() {

        Server direct = new Server()
                .url(directUrl)
                .description("Direct server");

        Contact contact = new Contact()
                .email("helloworld@gmail.com")
                .name("Hello World")
                .url("https://helloworld.com");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Messages API")
                .version("1.0")
                .contact(contact)
                .description("API secured by Keycloak using PKCE (no client-secret)")
                .termsOfService("http://helloworld.com")
                .license(mitLicense);

        SecurityScheme keycloakSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.OPENIDCONNECT)
                .openIdConnectUrl(keycloakBaseUrl + "/.well-known/openid-configuration")
                .description("OAuth2 flow via Keycloak (PKCE enabled)");

        return new OpenAPI()
                .info(info)
                .addServersItem(direct)
                .components(new Components().addSecuritySchemes("keycloak", keycloakSecurityScheme));

    }

}
