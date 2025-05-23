package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/message/{id}")
	public ResponseEntity<HelloWorldDto> helloWorld(@PathVariable Long id) {

		logger.info("Called BE method HelloWorldController.helloWorld() for id {}", id);

		HelloWorldDto helloWorldDto = helloWorldService.findById(id);
		return ResponseEntity.ok(helloWorldDto);		
		
	}
	
}