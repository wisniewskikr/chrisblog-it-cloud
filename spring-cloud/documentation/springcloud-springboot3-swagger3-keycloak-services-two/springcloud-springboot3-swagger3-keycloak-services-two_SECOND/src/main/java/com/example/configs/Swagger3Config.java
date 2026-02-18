package com.example.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {

    @Value("${swagger.app.uri}")
    public String appUrl;

    @Bean
    public OpenAPI secondServiceAPI() {

        Server server = new Server()
                .url(appUrl)
                .description("Second Service API");

        return new OpenAPI()
                .addServersItem(server)
                .info(new Info().title("Second Service API")
                        .description("This is the REST API for Second Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Second Service Documentation")
                        .url("https://www.google.com/"));
    }
}
