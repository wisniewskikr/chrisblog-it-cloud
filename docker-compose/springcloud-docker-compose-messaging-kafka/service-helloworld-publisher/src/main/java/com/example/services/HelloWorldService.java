package com.example.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldPublisherDto;
import com.google.gson.Gson;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private RabbitTemplate rabbitTemplate;
	
	@Value("${service.helloworld.message}")
	private String message;
	
	@Value("${queue.name}")
	private String queueName;

	public HelloWorldService(Environment environment, RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.environment = environment;
	}
	
	public HelloWorldPublisherDto getHelloWorldBeDto() {
		
		String port = environment.getProperty("local.server.port");
		String uuid = System.getProperty("uuid");		
		return new HelloWorldPublisherDto(message, port, uuid);
		
	}
	
	public void sendHelloWorld(HelloWorldPublisherDto helloWorld) {
		rabbitTemplate.convertAndSend(queueName, new Gson().toJson(helloWorld));
	}

}
