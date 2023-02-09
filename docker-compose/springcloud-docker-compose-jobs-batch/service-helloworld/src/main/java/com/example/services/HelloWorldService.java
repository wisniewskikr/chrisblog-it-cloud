package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;
import com.example.feigns.HelloWorldBatchService;

@Service
public class HelloWorldService {
	
	private HelloWorldBatchService helloWorldBatchService;
	
	private Environment environment;
	
	@Autowired
	public HelloWorldService(HelloWorldBatchService helloWorldBatchService, Environment environment) {
		this.helloWorldBatchService = helloWorldBatchService;
		this.environment = environment;
	}

	public HelloWorldDto getHelloWorldDto() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");	
		String message = helloWorldBatchService.getHelloWorldBatchDto().getMessage();
		return new HelloWorldDto(message, port, uuid);
		
	}

}
