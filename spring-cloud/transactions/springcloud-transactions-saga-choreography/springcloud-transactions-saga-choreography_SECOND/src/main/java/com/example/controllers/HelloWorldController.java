package com.example.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class HelloWorldController {

	@GetMapping
	public void helloWorld(@RequestBody String message) {

		log.info("Second message: {}", message);

	}
	
}