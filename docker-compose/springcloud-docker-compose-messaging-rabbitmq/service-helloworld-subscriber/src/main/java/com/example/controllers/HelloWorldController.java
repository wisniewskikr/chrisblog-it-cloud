package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldSubscriberDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value="/subscribe")
	public HelloWorldSubscriberDto helloWorld() {
				
		HelloWorldSubscriberDto helloWorldSubscriberDto = helloWorldService.getHelloWorldFeDto();		
		logger.info("Called servie HelloWorld FE with message {}, port of Publisher {}, uuid of Publisher {}, port of Subscriber {} and uuid of Subscriber", 
				helloWorldSubscriberDto.getMessage(), helloWorldSubscriberDto.getPortBe(), helloWorldSubscriberDto.getUuidBe(), helloWorldSubscriberDto.getPortFe(), helloWorldSubscriberDto.getUuidFe());		
		return helloWorldSubscriberDto;
		
	}	
	
}