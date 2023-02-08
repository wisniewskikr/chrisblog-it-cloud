package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;

@Service
public class HelloWorldService {
	
private Environment environment;
	
	@Value("${service.helloworld.message}")
	private String message;

	public HelloWorldService(Environment environment) {
		this.environment = environment;
	}
	
	public HelloWorldDto getHelloWorldDto() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");		
		return new HelloWorldDto(message, port, uuid);
		
	}

}
