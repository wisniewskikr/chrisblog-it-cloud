package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    private HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;        
    }

    @GetMapping("/")
    public ResponseEntity<HelloWorldFirstDto> defaultHelloWorld() {
        return publicHelloWorld();
    }

    @GetMapping("/public")
    public ResponseEntity<HelloWorldFirstDto> publicHelloWorld() {

        logger.info("Called FIRST method HelloWorldController.publicHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getPublicMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

    @GetMapping("/secured")
    public ResponseEntity<HelloWorldFirstDto> securedHelloWorld() {

        logger.info("Called FIRST method HelloWorldController.securedHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getSecuredMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

}
