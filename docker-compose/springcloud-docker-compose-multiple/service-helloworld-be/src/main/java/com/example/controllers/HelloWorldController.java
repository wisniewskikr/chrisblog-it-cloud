package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jsons.HelloWorldJson;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private Environment environment;
	
	@Value("${service.helloworld.message}")
	private String message;

	public HelloWorldController(Environment environment) {
		this.environment = environment;
	}

	@RequestMapping(value="/")
	public HelloWorldJson helloWorld() {
				
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");
		
		logger.info("Called servie HelloWorld with message {}, port {} and uuid {}", message, port, uuid);		
		return new HelloWorldJson(message, port, uuid);
		
	}
	
}