package com.example.controllers;

import com.example.services.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.services.HelloWorldService;

@RestController
@Slf4j
public class HelloWorldController {

    @Value("${global.message}")
    private String message;

    private HelloWorldService helloWorldService;

    private KafkaService kafkaService;

    private MessageSseController messageSseController;

    public HelloWorldController(HelloWorldService helloWorldService,
                                KafkaService kafkaService,
                                MessageSseController messageSseController) {
        this.helloWorldService = helloWorldService;
        this.kafkaService = kafkaService;
        this.messageSseController = messageSseController;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        helloWorldService.sendMessage(message);
        messageSseController.emitMessage(message);
        kafkaService.sendStatus("IN PROGRESS");
        return ResponseEntity.ok("Sent");

    }

}
