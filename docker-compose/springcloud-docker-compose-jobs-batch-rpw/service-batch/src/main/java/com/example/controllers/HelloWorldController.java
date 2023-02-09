package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.BatchDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {
	
	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value="/")
	public BatchDto helloWorld() {
		
		Long idText = Long.valueOf(1);
		String text = helloWorldService.readText(idText);
		
		return new BatchDto(text);		
	}
	
}