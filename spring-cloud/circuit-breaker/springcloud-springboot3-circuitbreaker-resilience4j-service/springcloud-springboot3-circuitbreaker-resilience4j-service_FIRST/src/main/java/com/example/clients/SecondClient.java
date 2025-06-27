package com.example.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface SecondClient {

    @GetExchange("/status/200")
    public ResponseEntity<String> status200();

    @GetExchange("/status/400")
    public ResponseEntity<String> status400();

    @GetExchange("/status/500")
    public ResponseEntity<String> status500();

    @GetExchange("/status/timeout")
    public ResponseEntity<String> statusTimeout();

}