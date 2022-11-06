package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jsons.HelloWorldJson;

@RestController
public class HelloWorldController {
	
	@Value("${service.helloworld.message}")
	private String message;
	
	private Environment environment;	
	
	@Autowired
	public HelloWorldController(Environment environment) {
		this.environment = environment;
	}

	@RequestMapping(value="/")
	public HelloWorldJson greeting() {
		
		String port = environment.getProperty("local.server.port");
		return new HelloWorldJson(message, port);
		
	}
	
}