package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldFeDto;
import com.example.feigns.HelloWorldBeClient;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private Environment environment;
	private HelloWorldBeClient helloWorldBeClient; 
	
	@Autowired
	public HelloWorldController(Environment environment, HelloWorldBeClient helloWorldBeClient) {
		this.environment = environment;
		this.helloWorldBeClient = helloWorldBeClient;
	}

	@RequestMapping(value="/")
	public HelloWorldFeDto helloWorld() {
				
		String message = helloWorldBeClient.getHelloWorldBeDto().getMessage();
		String portBe = helloWorldBeClient.getHelloWorldBeDto().getPort();
		String uuidBe = helloWorldBeClient.getHelloWorldBeDto().getUuid();
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");
		
		logger.info("Called servie HelloWorld FE with message {}, port of BE {}, uuid of BE {}, port of FE {} and uuid of FE", 
				message, portBe, uuidBe, portFe, uuidFe);		
		return new HelloWorldFeDto(message, portBe, uuidBe, portFe, uuidFe);
		
	}
	
}