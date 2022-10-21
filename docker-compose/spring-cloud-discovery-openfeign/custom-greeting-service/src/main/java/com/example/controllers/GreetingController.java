package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.clients.CustomTextServiceClient;
import com.example.responses.GreetingResponse;
import com.example.responses.TextResponse;

@RestController
public class GreetingController {

	private static final String VERSION = "1";
	
	private Environment environment;
	
	private CustomTextServiceClient customTextServiceClient;
	
	@Autowired
	public GreetingController(Environment environment, CustomTextServiceClient customTextServiceClient) {
		this.environment = environment;
		this.customTextServiceClient = customTextServiceClient;
	}

	@GetMapping(value="/greeting/lang/{lang}/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GreetingResponse greetingGet(
			@PathVariable(value = "lang") String lang,
			@PathVariable(value = "name") String name) {
		
		TextResponse textResponse = customTextServiceClient.provideText(lang);
		
		String port = environment.getProperty("local.server.port");
		return new GreetingResponse(
				textResponse.getText() + " " + name, 
				port, 
				VERSION, 
				textResponse.getPort(), 
				textResponse.getVersion());
		
	}	
	
}