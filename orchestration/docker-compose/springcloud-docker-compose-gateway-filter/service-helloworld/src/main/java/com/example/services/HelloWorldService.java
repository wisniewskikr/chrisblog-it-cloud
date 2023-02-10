package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class HelloWorldService {
	
private Environment environment;
	
	@Value("${request.header.name}")
	private String requestHeaderName;
	
	@Value("${response.header.name}")
	private String responseHeaderName;
	
	@Value("${response.header.value}")
	private String responseHeaderValue;

	public HelloWorldService(Environment environment) {
		this.environment = environment;
	}
	
	public HelloWorldDto getHelloWorldDto(HttpServletRequest request) {		
		
		String message = request.getHeader(requestHeaderName);
		System.out.println(String.format( "***** HelloWorldService: Header from filter RequestGatewayFilter with name %s and value %s:", requestHeaderName, message));
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");		
		return new HelloWorldDto(message, port, uuid);
		
	}
	
	public void handleResponse(HttpServletResponse response) {
		
		System.out.println(String.format( "***** HelloWorldService: Header to filter ResponseGatewayFilter with name %s and value %s:", responseHeaderName, responseHeaderValue));
		response.addHeader(responseHeaderName, responseHeaderValue);
		
	}

}
