package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldFeDto;
import com.example.feigns.HelloWorldBeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private HelloWorldBeClient helloWorldBeClient;
	
	@Autowired
	public HelloWorldService(Environment environment, HelloWorldBeClient helloWorldBeClient) {
		this.environment = environment;
		this.helloWorldBeClient = helloWorldBeClient;
	}
	
	@HystrixCommand(fallbackMethod = "noHelloWorldBe",
		commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "25")
		}
	)
	public HelloWorldFeDto getHelloWorldFeDto() {
				
		String message = helloWorldBeClient.getHelloWorldBeDto().getMessage();
		String portBe = helloWorldBeClient.getHelloWorldBeDto().getPort();
		String uuidBe = helloWorldBeClient.getHelloWorldBeDto().getUuid();
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");

		return new HelloWorldFeDto(message, portBe, uuidBe, portFe, uuidFe);
		
	}
	
	@SuppressWarnings("unused")
	private HelloWorldFeDto noHelloWorldBe() {
		
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");
		
		return new HelloWorldFeDto("no message", "no port BE", "no uuid BE", portFe, uuidFe);
		
	}	

}
