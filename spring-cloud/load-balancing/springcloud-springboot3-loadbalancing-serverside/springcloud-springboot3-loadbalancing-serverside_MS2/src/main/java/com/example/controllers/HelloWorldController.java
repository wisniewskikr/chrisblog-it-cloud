package com.example.controllers;

import com.example.models.ResponseMs2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ms2")
public class HelloWorldController {

	private final Environment environment;

	public HelloWorldController(Environment environment) {
		this.environment = environment;
	}

	@GetMapping
	public ResponseEntity<ResponseMs2> helloWorld() {

		String portMs2 = environment.getProperty("local.server.port");
		return ResponseEntity.ok(new ResponseMs2(portMs2));

	}
	
}