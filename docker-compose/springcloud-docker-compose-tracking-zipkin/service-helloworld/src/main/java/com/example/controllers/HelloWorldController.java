package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value="/")
	public HelloWorldDto helloWorld() {
				
		HelloWorldDto helloWorldDto = helloWorldService.getHelloWorldDto();		
		logger.info("Called servie HelloWorld with message {}, port {} and uuid {}", 
				helloWorldDto.getMessage(), helloWorldDto.getPort(), helloWorldDto.getUuid());		
		return helloWorldDto;
		
	}
	
}