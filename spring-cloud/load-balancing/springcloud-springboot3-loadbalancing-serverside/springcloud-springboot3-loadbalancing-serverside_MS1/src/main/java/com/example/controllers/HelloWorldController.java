package com.example.controllers;

import com.example.clients.ClientMs2;
import com.example.models.ResponseMs1;
import com.example.models.ResponseMs2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ms1")
public class HelloWorldController {

    private final Environment environment;
    private final ClientMs2 clientMs2;

    public HelloWorldController(Environment environment, ClientMs2 clientMs2) {
        this.environment = environment;
        this.clientMs2 = clientMs2;
    }

    @GetMapping
    public ResponseEntity<ResponseMs1> helloWorldMs1() {

        ResponseMs2 responseMs2 = clientMs2.helloWorld();
        String portMs1 = environment.getProperty("local.server.port");
        return ResponseEntity.ok(new ResponseMs1(portMs1, responseMs2.portMs2()));

    }

}
