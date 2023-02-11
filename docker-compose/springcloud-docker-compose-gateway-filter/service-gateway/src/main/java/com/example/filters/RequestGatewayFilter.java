package com.example.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class RequestGatewayFilter extends AbstractGatewayFilterFactory<RequestGatewayFilter.Config> {
	
	@Value("${request.header.name}")
	private String requestHeaderName;
	
	@Value("${request.header.value}")
	private String requestHeaderValue;
	
	public RequestGatewayFilter() {
        super(Config.class);
    }
		
	@Override
	public GatewayFilter apply(Config config) {
		
		System.out.println(String.format( "***** RequestGatewayFilter: Header to service HelloWorld with name %s and value %s:", requestHeaderName, requestHeaderValue));
		
		return (exchange, chain) -> {
			
			exchange
				.getRequest()
				.mutate()
				.headers(h -> h.add(requestHeaderName, requestHeaderValue)).build();
			
			ServerWebExchange modifiedExchange = exchange.mutate().build();
			
			return chain.filter(modifiedExchange);
		};
		
	}
	
	public static class Config {}

}
