package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.services.HelloWorldService;

@RestController
@AllArgsConstructor
public class HelloWorldController {

    private HelloWorldService helloWorldService;

    // TODO
//    @GetMapping("/")
//    public String defaultHelloWorld() {
//        return "redirect: /status/200";
//    }

    @GetMapping("/status/200")
    public ResponseEntity<String> status200() {
        return ResponseEntity.ok(helloWorldService.getStatus200Message());
    }

    @GetMapping("/status/400")
    public ResponseEntity<String> status400() {
        return ResponseEntity.ok(helloWorldService.getStatus400Message());
    }

    @GetMapping("/status/500")
    public ResponseEntity<String> status500() {
        return ResponseEntity.ok(helloWorldService.getStatus500Message());
    }

    @GetMapping("/status/timeout")
    public ResponseEntity<String> statusTimeout() {
        return ResponseEntity.ok(helloWorldService.getTimeoutMessage());
    }

}
