package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jsons.HelloWorldJson;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
//	@Value("${service.helloworld.message}")
	private String message = "Hello World!";
	
	private Environment environment;	
	
	@Autowired
	public HelloWorldController(Environment environment) {
		this.environment = environment;
	}

	@RequestMapping(value="/greeting")
	public HelloWorldJson helloWorld() {
		
		logger.info("Hello World");
		String port = environment.getProperty("local.server.port");
		return new HelloWorldJson(message, port);
		
	}
	
}