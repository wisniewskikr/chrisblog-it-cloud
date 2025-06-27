package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @GetMapping("/")
    public ResponseEntity<HelloWorldFirstDto> defaultHelloWorld() {
        return publicHelloWorld();
    }

    @GetMapping("/status/timeout")
    public ResponseEntity<HelloWorldFirstDto> publicHelloWorld() {

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getTimeoutMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

}
