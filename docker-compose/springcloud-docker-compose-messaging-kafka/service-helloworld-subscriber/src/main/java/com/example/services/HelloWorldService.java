package com.example.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldPublisherDto;
import com.example.dtos.HelloWorldSubscriberDto;
import com.google.gson.Gson;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private RabbitTemplate rabbitTemplate;
	
	@Value("${queue.name}")
	private String queueName;
	
	@Value("${default.message}")
	private String defaultMessage;
	
	@Autowired
	public HelloWorldService(Environment environment, RabbitTemplate rabbitTemplate) {
		this.environment = environment;
		this.rabbitTemplate = rabbitTemplate;
	}	
	
	public HelloWorldSubscriberDto getHelloWorldFeDto() {		
		
		Object queue = rabbitTemplate.receiveAndConvert(queueName);
		String jsonString = (queue != null) ? queue.toString() : null;			
		if (jsonString == null) {
			return new HelloWorldSubscriberDto(defaultMessage, null, null, null, null);
		}
		
		Gson gson = new Gson();
		HelloWorldPublisherDto helloWorldBeDto = gson.fromJson(jsonString, HelloWorldPublisherDto.class);		
		String message = helloWorldBeDto.getMessage();
		String portBe = helloWorldBeDto.getPort();
		String uuidBe = helloWorldBeDto.getUuid();
		String portFe = environment.getProperty("local.server.port");
		String uuidFe = System.getProperty("uuid");
		return new HelloWorldSubscriberDto(message, portBe, uuidBe, portFe, uuidFe);
		
	}

}
