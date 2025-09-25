package com.example.controllers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/be")
public class HelloWorldController {

	private String message = "There is no message from Producer via Kafka yet";

	@GetMapping
	public String helloWorld() {		
		return "Message from Producer via Kafka is: " + message;
		
	}
		
	@KafkaListener(topics = "#{'${topic.name}'}")
	public void helloWorldListener(String message) {
		this.message = message;		
		System.out.println(message);		
	}
	
}