package com.example.configs;

import java.util.UUID;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ListenerConfig {
	
	@EventListener(ApplicationReadyEvent.class)
    public void createId() {
		System.setProperty("uuidApplication", UUID.randomUUID().toString());
	}

}