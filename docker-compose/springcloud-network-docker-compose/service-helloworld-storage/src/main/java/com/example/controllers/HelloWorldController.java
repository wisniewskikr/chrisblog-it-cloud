package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jsons.HelloWorldStorageJson;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Value("${service.helloworld.message}")
	private String message;

	@RequestMapping(value="/")
	public HelloWorldStorageJson helloWorld() {
		
		logger.info("Service Hello World Storage with uuid: " + System.getProperty("uuidApplication"));		
		return new HelloWorldStorageJson(message, System.getProperty("uuidApplication"));
		
	}
	
}