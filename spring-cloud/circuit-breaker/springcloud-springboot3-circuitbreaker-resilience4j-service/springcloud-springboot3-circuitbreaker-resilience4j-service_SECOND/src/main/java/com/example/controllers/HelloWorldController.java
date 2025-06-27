package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloWorldController {

	@GetMapping("/status/200")
	public ResponseEntity<String> status200() {
		return ResponseEntity.ok("Status 200");
	}

	@GetMapping("/status/timeout")
	public ResponseEntity<String> statusTimeout() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("Status Timeout");
	}
	
}