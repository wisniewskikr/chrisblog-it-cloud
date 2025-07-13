package com.example.controllers;

import com.example.services.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class HelloWorldController {

    @Value("${global.message}")
    private String message;

    private KafkaService kafkaService;

    private MessageSseController messageSseController;

    public HelloWorldController(KafkaService kafkaService,
                                MessageSseController messageSseController) {
        this.kafkaService = kafkaService;
        this.messageSseController = messageSseController;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        kafkaService.sendMessage(message);
        kafkaService.sendStatus("IN PROGRESS");
        messageSseController.emitMessage(message);
        return ResponseEntity.ok("Sent");

    }

}
