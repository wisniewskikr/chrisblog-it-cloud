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

    @Value("${service.url}")
    private String serviceUrl;

    @Bean
    public RouterFunction<ServerResponse> publicServiceRoute() {
        return GatewayRouterFunctions.route("publicService")
                .route(RequestPredicates.path("/public"), HandlerFunctions.http(serviceUrl + "/public"))                
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("userService")
                .route(RequestPredicates.path("/user"), HandlerFunctions.http(serviceUrl + "/user"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> adminServiceRoute() {
        return GatewayRouterFunctions.route("adminService")
                .route(RequestPredicates.path("/admin"), HandlerFunctions.http(serviceUrl + "/admin"))
                .build();
    }
    
}