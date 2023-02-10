package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;
import com.example.feigns.BatchService;

@Service
public class HelloWorldService {
	
	private BatchService batchService;
	
	private Environment environment;
	
	@Autowired
	public HelloWorldService(BatchService batchService, Environment environment) {
		this.batchService = batchService;
		this.environment = environment;
	}

	public HelloWorldDto getHelloWorldDto() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");	
		String message = batchService.getBatchDto().getMessage();
		return new HelloWorldDto(message, port, uuid);
		
	}
	
	public HelloWorldDto runBatchJob() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");	
		String message = batchService.runBatchJob().getMessage();
		return new HelloWorldDto(message, port, uuid);
		
	}

}
