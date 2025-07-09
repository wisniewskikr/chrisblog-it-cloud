package com.example.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

	private String message = "There is no message from Producer via Kafka yet";

	@GetMapping
	public String helloWorld() {		
		return "Message from Producer via Kafka is: " + getMessage();
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}