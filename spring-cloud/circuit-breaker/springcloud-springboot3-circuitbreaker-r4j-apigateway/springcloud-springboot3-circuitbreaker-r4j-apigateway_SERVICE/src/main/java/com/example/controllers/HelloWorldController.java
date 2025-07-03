package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloWorldController {

	@GetMapping("/status/200")
	public ResponseEntity<String> status200() {
		return ResponseEntity.ok("Service returns status 200");
	}

	@GetMapping("/status/400")
	public ResponseEntity<String> status400() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service returns status 400");
	}

	@GetMapping("/status/500")
	public ResponseEntity<String> status500() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service returns status 500");
	}

	@GetMapping("/status/timeout")
	public ResponseEntity<String> statusTimeout() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("Service returns status Timeout");
	}
	
}