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
    public RouterFunction<ServerResponse> defaultRoute() {
        return GatewayRouterFunctions.route("default")
                .route(RequestPredicates.path("/"), HandlerFunctions.http(firstServiceUrl))                
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> publicRoute() {
        return GatewayRouterFunctions.route("public")
                .route(RequestPredicates.path("/public"), HandlerFunctions.http(firstServiceUrl))                
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> securedRoute() {
        return GatewayRouterFunctions.route("secured")
                .route(RequestPredicates.path("/secured"), HandlerFunctions.http(firstServiceUrl))                
                .build();
    }
    
}