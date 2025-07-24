package com.example.controllers;

import com.example.models.HelloWorldResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class HelloWorldController {

    @Value("${message.common}")
    private String commonMessage;

    @Value("${message.public}")
    private String publicMessage;

    @Value("${message.secret}")
    private String secretMessage;

    @GetMapping
    public ResponseEntity<HelloWorldResponse> helloWorld() {
        return ResponseEntity.ok(new HelloWorldResponse(commonMessage, publicMessage, secretMessage));
    }

}
