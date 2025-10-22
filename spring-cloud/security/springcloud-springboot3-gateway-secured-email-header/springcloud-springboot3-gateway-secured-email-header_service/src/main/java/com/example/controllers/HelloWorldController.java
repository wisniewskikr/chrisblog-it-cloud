package com.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/public")
	public ResponseEntity<String> helloWorldPublic() {
		return ResponseEntity.ok("Hello World, Public!");		
	}

	@GetMapping("/secured")
	public ResponseEntity<String> helloWorldSecured(@AuthenticationPrincipal Jwt jwt) {
		// Keycloak typically puts email under "email" claim
		String email = jwt.getClaim("email");

		return ResponseEntity.ok("Hello World, Secured! Email: " + email);
	}
	
}