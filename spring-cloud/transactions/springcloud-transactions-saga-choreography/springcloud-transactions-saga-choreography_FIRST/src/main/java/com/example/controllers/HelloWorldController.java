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

    private StatusSseController statusSseController;

    public HelloWorldController(HelloWorldService helloWorldService,
                                KafkaService kafkaService,
                                MessageSseController messageSseController,
                                StatusSseController statusSseController) {
        this.helloWorldService = helloWorldService;
        this.kafkaService = kafkaService;
        this.messageSseController = messageSseController;
        this.statusSseController = statusSseController;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        helloWorldService.sendMessage(message);
        kafkaService.sendMessage(message);
        messageSseController.emitMessage(message);
        statusSseController.emitStatus("CREATED");
        return ResponseEntity.ok("Sent");

    }

}
