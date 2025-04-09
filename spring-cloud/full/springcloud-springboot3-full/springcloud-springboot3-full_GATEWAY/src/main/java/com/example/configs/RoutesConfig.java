package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

@Configuration
public class RoutesConfig {

    @Value("${first.service.url}")
    private String firstServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> firstServiceRoute() {
        return GatewayRouterFunctions.route("first_service")
                .route(RequestPredicates.path("/"), HandlerFunctions.http(firstServiceUrl))                
                .build();
    }
    
}