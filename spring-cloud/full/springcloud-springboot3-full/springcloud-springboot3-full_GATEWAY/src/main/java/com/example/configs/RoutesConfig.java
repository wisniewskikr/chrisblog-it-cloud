package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class RoutesConfig {

    @Value("${first.service.url}")
    private String firstServiceUrl;

    @Value("${second.service.url}")
    private String secondServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> defaultRoute() {
        return GatewayRouterFunctions.route("default")
                .route(RequestPredicates.path("/*"), HandlerFunctions.http(firstServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> firstServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("first_service_swagger")
                .route(RequestPredicates.path("/aggregate/first-service/v3/api-docs"), HandlerFunctions.http(firstServiceUrl))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> secondServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("second_service_swagger")
                .route(RequestPredicates.path("/aggregate/second-service/v3/api-docs"), HandlerFunctions.http(secondServiceUrl))
                .filter(setPath("/api-docs"))
                .build();
    }
    
}