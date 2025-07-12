package com.example.controllers;

import com.example.services.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController {

	private final KafkaService kafkaService;

	public HelloWorldController(KafkaService kafkaService) {
		this.kafkaService = kafkaService;
	}

	@GetMapping
	public void helloWorld(@RequestBody String message) {

		log.info("Second message: {}", message);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        kafkaService.sendStatus("DONE");


	}
	
}