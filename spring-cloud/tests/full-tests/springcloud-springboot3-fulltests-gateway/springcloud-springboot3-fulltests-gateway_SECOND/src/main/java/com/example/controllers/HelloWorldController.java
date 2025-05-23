package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {

	private HelloWorldService helloWorldService;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping("/message/{id}")
	public ResponseEntity<HelloWorldSecondDto> helloWorld(@PathVariable Long id) {

		HelloWorldSecondDto helloWorldSecondDto = helloWorldService.findById(id);
		return ResponseEntity.ok(helloWorldSecondDto);
		
	}
	
}