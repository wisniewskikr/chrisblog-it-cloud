package com.example.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
@Slf4j
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @GetMapping("/")
    public ResponseEntity<HelloWorldFirstDto> defaultHelloWorld() {
        return publicHelloWorld();
    }

    @GetMapping("/public")
    public ResponseEntity<HelloWorldFirstDto> publicHelloWorld() {

        log.info("Called FIRST method HelloWorldController.publicHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getPublicMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

    @GetMapping("/secured")
    public ResponseEntity<HelloWorldFirstDto> securedHelloWorld() {

        log.info("Called FIRST method HelloWorldController.securedHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getSecuredMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

}
