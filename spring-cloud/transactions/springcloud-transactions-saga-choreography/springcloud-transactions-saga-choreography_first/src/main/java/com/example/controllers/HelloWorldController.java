package com.example.controllers;

import com.example.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {	
	
	private HelloWorldService helloWorldService;

	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value="/helloworld/name/{name}")
	public String helloWorld(@PathVariable(name = "name") String name) {
		
		String message = "Hello World " + name;
		return helloWorldService.sendMessage(message);
		
	}
	
}