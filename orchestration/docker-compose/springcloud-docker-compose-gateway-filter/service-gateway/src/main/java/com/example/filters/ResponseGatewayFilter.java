package com.example.filters;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class ResponseGatewayFilter extends AbstractGatewayFilterFactory<ResponseGatewayFilter.Config> {
	
	@Value("${response.header.name}")
	private String responseHeaderName;
	
	public ResponseGatewayFilter() {
        super(Config.class);
    }
		
	@Override
	public GatewayFilter apply(Config config) {
		
		return (exchange, chain) -> {
		    return chain.filter(exchange)
		      .then(Mono.fromRunnable(() -> {
		          ServerHttpResponse response = exchange.getResponse();
		          
		          List<String> list = response.getHeaders().get(responseHeaderName);
		          if (list != null && !list.isEmpty()) {
		        	  String message = response.getHeaders().get(responseHeaderName).get(0);
			          System.out.println(String.format( "***** ResponseGatewayFilter: Header from service HelloWorld with name %s and value %s:", responseHeaderName, message));		          
			          response.getHeaders().add("new-" + responseHeaderName, "New " + message);	
		          }		          

		        }));
		};
		
	}
	
	public static class Config {}

}
