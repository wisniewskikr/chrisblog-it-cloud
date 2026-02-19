package com.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldFirstDto;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Messages", description = "First APIs")
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @GetMapping("/")
    public ResponseEntity<HelloWorldFirstDto> defaultHelloWorld() {
        return publicHelloWorld();
    }

    @Operation(
            summary = "Read public message",
            description = "Read public message",
            security = {})
    @GetMapping("/public")
    public ResponseEntity<HelloWorldFirstDto> publicHelloWorld() {

        log.info("Called FIRST method HelloWorldController.publicHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getPublicMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

    @Operation(
            summary = "Read secured message",
            description = "Read secured message")
    @GetMapping("/secured")
    public ResponseEntity<HelloWorldFirstDto> securedHelloWorld() {

        log.info("Called FIRST method HelloWorldController.securedHelloWorld()");

        HelloWorldFirstDto helloWorldFirstDto = helloWorldService.getSecuredMessage();
        return ResponseEntity.ok(helloWorldFirstDto);

    }

}
