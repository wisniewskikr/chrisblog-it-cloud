package com.example.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ResponseGatewayFilter extends AbstractGatewayFilterFactory<ResponseGatewayFilter.Config> {
	
	public ResponseGatewayFilter() {
        super(Config.class);
    }
		
	@Override
	public GatewayFilter apply(Config config) {
		
		return (exchange, chain) -> {
		    return chain.filter(exchange)
		      .then(Mono.fromRunnable(() -> {
		          ServerHttpResponse response = exchange.getResponse();
		          response.getHeaders().add("name", "value");	

		        }));
		};
		
	}
	
	public static class Config {}

}
