package com.example.controllers;

import com.example.dtos.HelloWorldFirstDto;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldSecondDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {

    private HelloWorldService helloWorldService;
    private Environment environment; 

    public HelloWorldController(HelloWorldService helloWorldService, Environment environment) {
        this.helloWorldService = helloWorldService;
        this.environment = environment;
    }

    @GetMapping("/public")
    ResponseEntity<HelloWorldFirstDto> publicMethod() {
        HelloWorldSecondDto helloWorldSecondDto = helloWorldService.findById(1L);
        return ResponseEntity.ok(new HelloWorldFirstDto(1L, helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond()));
    }

    @GetMapping("/secured")
    ResponseEntity<HelloWorldFirstDto> securedMethod() {
        HelloWorldSecondDto helloWorldSecondDto = helloWorldService.findById(2L);
        return ResponseEntity.ok(new HelloWorldFirstDto(1L, helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond()));
    }

}
