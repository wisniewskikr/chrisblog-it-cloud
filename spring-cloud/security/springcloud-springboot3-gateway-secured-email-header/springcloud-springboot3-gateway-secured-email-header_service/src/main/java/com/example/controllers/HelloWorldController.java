package com.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/public")
	public ResponseEntity<String> helloWorldPublic() {
		return ResponseEntity.ok("Hello World, Public!");		
	}

	@GetMapping("/secured")
	public ResponseEntity<String> helloWorldSecured(@RequestHeader(value = "email", required = false) String email) {
		return ResponseEntity.ok("Hello World, Secured! Email: " + email);
	}
	
}