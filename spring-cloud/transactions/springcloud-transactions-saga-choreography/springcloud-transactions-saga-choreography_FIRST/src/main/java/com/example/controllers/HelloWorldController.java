package com.example.controllers;

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

    private MessageSseController messageSseController;

    private StatusSseController statusSseController;

    public HelloWorldController(HelloWorldService helloWorldService,
                                MessageSseController messageSseController,
                                StatusSseController statusSseController) {
        this.helloWorldService = helloWorldService;
        this.messageSseController = messageSseController;
        this.statusSseController = statusSseController;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        helloWorldService.sendMessage(message);
        messageSseController.emitMessage(message);
        statusSseController.emitStatus("CREATED");
        return ResponseEntity.ok("Sent");

    }

}
