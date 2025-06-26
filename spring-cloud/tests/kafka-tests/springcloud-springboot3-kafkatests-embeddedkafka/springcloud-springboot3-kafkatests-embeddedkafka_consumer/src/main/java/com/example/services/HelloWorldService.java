package com.example.services;

import com.example.controllers.HelloWorldController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    private HelloWorldController helloWorldController;

    public HelloWorldService(HelloWorldController helloWorldController) {
        this.helloWorldController = helloWorldController;
    }

    @KafkaListener(topics = "#{'${topic.name}'}")
    public void helloWorldListener(String message) {
        helloWorldController.setMessage(message);
        System.out.println(message);
    }

}
