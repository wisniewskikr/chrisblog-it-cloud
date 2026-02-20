package com.example.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class RoutesConfig {

    @Value("${service.first.url}")
    private String serviceFirstUrl;

    @Value("${service.second.url}")
    private String serviceSecondUrl;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Bean
    public RouterFunction<ServerResponse> firstServiceRoute() {
        return GatewayRouterFunctions.route("first_service")
                .route(RequestPredicates.path("/*"), HandlerFunctions.http(serviceFirstUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> firstServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("first_service_swagger")
                .route(RequestPredicates.path("/aggregate/first-service/v3/api-docs"),
                        HandlerFunctions.http(serviceFirstUrl))
                .filter((request, next) -> {
                    Authentication authentication =
                            SecurityContextHolder.getContext().getAuthentication();

                    ServerRequest mutatedRequest = request;
                    if (authentication instanceof OAuth2AuthenticationToken token) {
                        OAuth2AuthorizedClient client =
                                authorizedClientService.loadAuthorizedClient(
                                        token.getAuthorizedClientRegistrationId(),
                                        token.getName());

                        if (client != null) {
                            // Mutate the request to add Authorization header
                            mutatedRequest = ServerRequest.from(request)
                                    .header(HttpHeaders.AUTHORIZATION,
                                            "Bearer " + client.getAccessToken().getTokenValue())
                                    .build();
                        }
                    }

                    return next.handle(mutatedRequest);
                })
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> secondServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("second_service_swagger")
                .route(RequestPredicates.path("/aggregate/second-service/v3/api-docs"),
                        HandlerFunctions.http(serviceFirstUrl))
                .filter((request, next) -> {
                    Authentication authentication =
                            SecurityContextHolder.getContext().getAuthentication();

                    ServerRequest mutatedRequest = request;
                    if (authentication instanceof OAuth2AuthenticationToken token) {
                        OAuth2AuthorizedClient client =
                                authorizedClientService.loadAuthorizedClient(
                                        token.getAuthorizedClientRegistrationId(),
                                        token.getName());

                        if (client != null) {
                            // Mutate the request to add Authorization header
                            mutatedRequest = ServerRequest.from(request)
                                    .header(HttpHeaders.AUTHORIZATION,
                                            "Bearer " + client.getAccessToken().getTokenValue())
                                    .build();
                        }
                    }

                    return next.handle(mutatedRequest);
                })
                .filter(setPath("/api-docs"))
                .build();
    }
    
}