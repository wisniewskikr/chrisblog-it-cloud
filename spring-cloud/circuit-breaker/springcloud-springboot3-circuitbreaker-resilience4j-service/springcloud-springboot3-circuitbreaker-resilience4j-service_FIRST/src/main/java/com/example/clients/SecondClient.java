package com.example.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "status500fallback", fallbackMethod = "status500Fallback")
    @Retry(name = "status500fallback")
    public ResponseEntity<String> status500();

    @GetExchange("/status/timeout")
    public ResponseEntity<String> statusTimeout();

    default ResponseEntity<String> status500Fallback(Throwable throwable) {
        System.out.println("First service handles status 500 using CircuitBreaker. Error details: " + throwable.getMessage());
        return ResponseEntity.ok("Temporary problem with the application. It seems that external service is unavailable");
    }

}