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

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        helloWorldService.sendMessage(message);
        return ResponseEntity.ok(message);
    }
}
