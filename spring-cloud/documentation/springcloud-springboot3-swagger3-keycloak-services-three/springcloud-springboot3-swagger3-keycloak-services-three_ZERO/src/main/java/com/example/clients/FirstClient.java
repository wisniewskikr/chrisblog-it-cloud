package com.example.clients;

import com.example.dtos.HelloWorldFirstDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface FirstClient {

    @GetExchange("/public")
    ResponseEntity<HelloWorldFirstDto> publicHelloWorld();

    @GetExchange("/secured")
    public ResponseEntity<HelloWorldFirstDto> securedHelloWorld();

}