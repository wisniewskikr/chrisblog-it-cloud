package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldFeDto;
import com.example.services.HelloWorldService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value="/")
	@HystrixCommand(fallbackMethod = "noHelloWorldBe",
		commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "25")
		}
	)
	public HelloWorldFeDto helloWorld() {
				
		HelloWorldFeDto helloWorldFeDto = helloWorldService.getHelloWorldFeDto();		
		logger.info("Called servie HelloWorld FE with message {}, port of BE {}, uuid of BE {}, port of FE {} and uuid of FE", 
				helloWorldFeDto.getMessage(), helloWorldFeDto.getPortBe(), helloWorldFeDto.getUuidBe(), helloWorldFeDto.getPortFe(), helloWorldFeDto.getUuidFe());		
		return new HelloWorldFeDto(helloWorldFeDto.getMessage(), helloWorldFeDto.getPortBe(), helloWorldFeDto.getUuidBe(), helloWorldFeDto.getPortFe(), helloWorldFeDto.getUuidFe());
		
	}	
	
}