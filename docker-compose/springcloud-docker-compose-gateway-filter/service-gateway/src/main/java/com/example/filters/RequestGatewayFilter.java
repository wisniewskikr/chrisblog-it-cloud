package com.example.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class RequestGatewayFilter extends AbstractGatewayFilterFactory<RequestGatewayFilter.Config> {
	
	public RequestGatewayFilter() {
        super(Config.class);
    }
		
	@Override
	public GatewayFilter apply(Config config) {
		
		return (exchange, chain) -> {
		    return chain.filter(exchange)
		      .then(Mono.fromRunnable(() -> {
		    	  
		          ServerHttpRequest request = exchange.getRequest();
		          request.getHeaders().add("FILTER_TYPE", "GATEWAY_REQUEST");	

		        }));
		};
		
	}
	
	public static class Config {}

}
