package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fe")
public class HelloWorldController {

	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topic.name}")
	private String topicName;

	@Autowired
	public HelloWorldController(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@GetMapping
	public String helloWorld(@RequestParam String name) {

        logger.info("FE Producer called for name: {}", name);
		
		String message = "Hello World " + name;
		kafkaTemplate.send(topicName, message);
		return "The message was sent to Consumer via Kafka";
		
	}
	
}