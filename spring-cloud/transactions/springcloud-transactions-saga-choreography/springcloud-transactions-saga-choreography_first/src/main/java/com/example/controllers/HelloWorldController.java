package com.example.controllers;

import com.example.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class HelloWorldController {	
	
	private HelloWorldService helloWorldService;

	private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

	public HelloWorldController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping("/sse")
	public SseEmitter streamSse() {
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
		emitters.add(emitter);
		emitter.onCompletion(() -> emitters.remove(emitter));
		emitter.onTimeout(() -> emitters.remove(emitter));
		return emitter;
	}

	@GetMapping(value="/helloworld/name/{name}")
	public void helloWorld(@PathVariable(name = "name") String name) {
		
		String message = "Hello World " + name;
		helloWorldService.sendMessage(message);

		List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().data(message));
			} catch (IOException e) {
				deadEmitters.add(emitter);
			}
		}
		emitters.removeAll(deadEmitters);
		
	}
	
}