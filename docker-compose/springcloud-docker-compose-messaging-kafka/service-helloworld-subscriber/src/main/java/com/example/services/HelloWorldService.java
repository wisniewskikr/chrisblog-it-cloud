package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldPublisherDto;
import com.example.dtos.HelloWorldSubscriberDto;
import com.google.gson.Gson;

@Service
public class HelloWorldService {
	
	private Environment environment;
	private List<String> messages;
	
	@Value("${default.message}")
	private String defaultMessage;
	
	@Autowired
	public HelloWorldService(Environment environment) {
		this.environment = environment;
		this.messages = new ArrayList<String>();
	}
	
	@KafkaListener(topics = "#{'${topic.name}'}")
	public void helloWorldListener(String message) {
		System.out.println("Message: " + message);
		messages.add(message);		
	}
	
	public HelloWorldSubscriberDto getHelloWorldFeDto() {		
		
		String jsonString = (messages.isEmpty()) ? null : messages.remove(0);			
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
