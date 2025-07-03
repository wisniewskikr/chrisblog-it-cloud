package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class RoutesConfig {

    @Value("${service.url}")
    private String serviceUrl;

    @Bean
    public RouterFunction<ServerResponse> serviceRoute() {
        return GatewayRouterFunctions.route("service")
                .route(RequestPredicates.path("/status/*"), HandlerFunctions.http(serviceUrl))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("fallback-service",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Temporary problem with the application. It seems that external service is unavailable."))
                .build();
    }
    
}