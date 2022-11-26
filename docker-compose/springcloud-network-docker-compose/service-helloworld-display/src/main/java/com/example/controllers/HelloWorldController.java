package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feigns.StorageFeignClient;
import com.example.jsons.HelloWorldDisplayJson;
import com.example.jsons.HelloWorldStorageJson;

@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	private StorageFeignClient storageFeignClient;
	
	@Autowired
	public HelloWorldController(StorageFeignClient storageFeignClient) {
		this.storageFeignClient = storageFeignClient;
	}

	@RequestMapping(value="/")
	public HelloWorldDisplayJson helloWorld() {
		
		logger.info("Service Hello World Display with uuid: " + System.getProperty("uuidApplication"));		
		HelloWorldStorageJson storageJson = storageFeignClient.getHelloWorldFromStorage();
		return new HelloWorldDisplayJson(storageJson.getMessage(), System.getProperty("uuidApplication"), storageJson.getUuid());
		
	}
	
}