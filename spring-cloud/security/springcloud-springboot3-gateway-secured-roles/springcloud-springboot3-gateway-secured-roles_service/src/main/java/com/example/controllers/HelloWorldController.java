package com.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/public")
	public ResponseEntity<String> helloWorldPublic() {
		return ResponseEntity.ok("Hello World, Public!");		
	}

	@GetMapping("/user")
	public ResponseEntity<String> helloWorldUser() {
		return ResponseEntity.ok("Hello World, User!");
	}

	@GetMapping("/admin")
	public ResponseEntity<String> helloWorldAdmin() {
		return ResponseEntity.ok("Hello World, Admin!");
	}
	
}