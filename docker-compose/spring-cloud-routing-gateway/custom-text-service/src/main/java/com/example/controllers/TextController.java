package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.entities.TextEntity;
import com.example.responses.TextResponse;
import com.example.services.TextService;

@RestController
public class TextController {

	private static final String VERSION = "1";
	
	private Environment environment;	
	private TextService textService;
	
	@Autowired
	public TextController(Environment environment, TextService textService) {
		this.environment = environment;
		this.textService = textService;
	}

	@GetMapping(value="/text/lang/{lang}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TextResponse provideText(@PathVariable(value = "lang") String lang) {
		
		TextEntity text = textService.findByLang(lang);		
		String port = environment.getProperty("local.server.port");
		return new TextResponse(text.getValue(), port, VERSION);
		
	}	
	
}