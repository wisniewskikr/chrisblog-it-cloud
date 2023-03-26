package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldPublisherDto;
import com.google.gson.Gson;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topic.name}")
	private String topicName;
	
	@Value("${service.helloworld.message}")
	private String message;

	public HelloWorldService(Environment environment, KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.environment = environment;
	}
	
	public HelloWorldPublisherDto getHelloWorldBeDto() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");		
		return new HelloWorldPublisherDto(message, port, uuid);
		
	}
	
	public void sendHelloWorld(HelloWorldPublisherDto helloWorld) {
		kafkaTemplate.send(topicName, new Gson().toJson(helloWorld));
	}

}
