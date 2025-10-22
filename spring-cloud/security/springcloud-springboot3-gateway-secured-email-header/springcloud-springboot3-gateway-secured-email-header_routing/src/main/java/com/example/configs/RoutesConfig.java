package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.RouterFunction;
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
    public RouterFunction<ServerResponse> securedServiceRoute() {
        return GatewayRouterFunctions.route("securedService")
                .route(RequestPredicates.path("/secured"), HandlerFunctions.http(serviceUrl + "/secured"))
                .filter(this::addEmailHeader)
                .build();
    }

    private ServerResponse addEmailHeader(ServerRequest request,
                                          org.springframework.web.servlet.function.HandlerFunction<ServerResponse> next) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            String email = jwt.getClaimAsString("email");
            if (email != null) {
                // Forward with additional header
                ServerRequest newRequest = ServerRequest.from(request)
                        .header("email", email)
                        .build();
                return next.handle(newRequest);
            }
        }

        return next.handle(request);
    }

}
