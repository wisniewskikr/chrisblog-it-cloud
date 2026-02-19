package com.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldZeroDto;
import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Messages", description = "First APIs")
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    @GetMapping("/")
    public ResponseEntity<HelloWorldZeroDto> defaultHelloWorld() {
        return publicHelloWorld();
    }

    @Operation(
            summary = "Read public message",
            description = "Read public message",
            security = {})
    @GetMapping("/public")
    public ResponseEntity<HelloWorldZeroDto> publicHelloWorld() {

        log.info("Called ZERO method HelloWorldController.publicHelloWorld()");

        HelloWorldZeroDto HelloWorldZeroDto = helloWorldService.getPublicMessage();
        return ResponseEntity.ok(HelloWorldZeroDto);

    }

    @Operation(
            summary = "Read secured message",
            description = "Read secured message",
            security = @SecurityRequirement(name = "keycloak"))
    @GetMapping("/secured")
    public ResponseEntity<HelloWorldZeroDto> securedHelloWorld() {

        log.info("Called ZERO method HelloWorldController.securedHelloWorld()");

        HelloWorldZeroDto HelloWorldZeroDto = helloWorldService.getSecuredMessage();
        return ResponseEntity.ok(HelloWorldZeroDto);

    }

}
