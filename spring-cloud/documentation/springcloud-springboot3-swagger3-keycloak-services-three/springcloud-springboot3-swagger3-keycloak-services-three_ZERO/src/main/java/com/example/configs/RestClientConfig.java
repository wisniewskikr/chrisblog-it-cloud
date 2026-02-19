package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.clients.FirstClient;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.http.HttpHeaders;

@Configuration
public class RestClientConfig {

    @Value("${api.url}")
    private String apiUrl;

    @Bean
    public FirstClient beClient(RestClient.Builder restClientBuilder) {

        RestClient restClient = restClientBuilder
                .baseUrl(apiUrl)
                .requestInterceptor((request, body, execution) -> {

                    var authentication = SecurityContextHolder.getContext().getAuthentication();

                    if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                        String token = jwtAuth.getToken().getTokenValue();
                        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    }

                    return execution.execute(request, body);
                })
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(FirstClient.class);
    }

}