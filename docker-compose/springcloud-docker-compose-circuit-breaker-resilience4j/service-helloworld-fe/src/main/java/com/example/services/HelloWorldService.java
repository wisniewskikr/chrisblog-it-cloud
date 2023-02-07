package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldBeDto;
import com.example.dtos.HelloWorldFeDto;
import com.example.feigns.HelloWorldBeClient;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private HelloWorldBeClient helloWorldBeClient;
	private CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	public HelloWorldService(Environment environment, HelloWorldBeClient helloWorldBeClient,
			CircuitBreakerFactory circuitBreakerFactory) {
		this.environment = environment;
		this.helloWorldBeClient = helloWorldBeClient;
		this.circuitBreakerFactory = circuitBreakerFactory;
	}	
	
	public HelloWorldFeDto getHelloWorldFeDto() {
		
		HelloWorldBeDto helloWorldBeDto = circuitBreakerFactory.create("getHelloWorldBeDto").run(() -> helloWorldBeClient.getHelloWorldBeDto(),
                t -> new HelloWorldBeDto("no message", "no port BE", "no uuid BE"));
		
		String message = helloWorldBeDto.getMessage();
		String portBe = helloWorldBeDto.getPort();
		String uuidBe = helloWorldBeDto.getUuid();
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");

		return new HelloWorldFeDto(message, portBe, uuidBe, portFe, uuidFe);
		
	}

	
	

}
