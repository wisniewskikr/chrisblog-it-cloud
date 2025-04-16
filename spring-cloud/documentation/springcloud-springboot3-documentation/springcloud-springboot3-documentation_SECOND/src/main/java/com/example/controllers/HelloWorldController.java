package com.example.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
@Slf4j
public class HelloWorldController {

	private HelloWorldService helloWorldService;

	@GetMapping("/message/{id}")
	public ResponseEntity<HelloWorldSecondDto> helloWorld(@PathVariable Long id) {

		log.info("Called SECOND method HelloWorldController.helloWorld() for id {}", id);

		HelloWorldSecondDto helloWorldSecondDto = helloWorldService.findById(id);
		return ResponseEntity.ok(helloWorldSecondDto);		
		
	}
	
}