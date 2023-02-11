package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldBeDto;
import com.example.dtos.HelloWorldFeDto;
import com.example.feigns.HelloWorldBeService;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private HelloWorldBeService helloWorldBeService;
	private CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	public HelloWorldService(Environment environment, HelloWorldBeService helloWorldBeService,
			CircuitBreakerFactory circuitBreakerFactory) {
		this.environment = environment;
		this.helloWorldBeService = helloWorldBeService;
		this.circuitBreakerFactory = circuitBreakerFactory;
	}	
	
	public HelloWorldFeDto getHelloWorldFeDto() {
		
		HelloWorldBeDto helloWorldBeDto = circuitBreakerFactory.create("getHelloWorldBeDto").run(() -> helloWorldBeService.getHelloWorldBeDto(),
                t -> new HelloWorldBeDto("no message", "no port BE", "no uuid BE"));
		
		String message = helloWorldBeDto.getMessage();
		String portBe = helloWorldBeDto.getPort();
		String uuidBe = helloWorldBeDto.getUuid();
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");

		return new HelloWorldFeDto(message, portBe, uuidBe, portFe, uuidFe);
		
	}

	
	

}
