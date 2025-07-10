package com.example.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
@Slf4j
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @GetMapping
    public ResponseEntity<String> helloWorld() {

        String message = "Hello World!";
        helloWorldService.sendMessage(message);
        return ResponseEntity.ok(message);

    }

}
