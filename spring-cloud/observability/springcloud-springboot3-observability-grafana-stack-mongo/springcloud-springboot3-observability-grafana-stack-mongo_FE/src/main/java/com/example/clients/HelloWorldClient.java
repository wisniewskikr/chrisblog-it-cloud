package com.example.clients;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.dtos.HelloWorldDto;

@Component
public class HelloWorldClient {

    private final RestTemplate beRestTemplate;

    public HelloWorldClient(RestTemplate beRestTemplate) {
        this.beRestTemplate = beRestTemplate;
    }

    public HelloWorldDto findById(Long id) {
        return beRestTemplate.exchange("/message/" + id,
                        HttpMethod.GET, null, HelloWorldDto.class)
                .getBody();
    }

}