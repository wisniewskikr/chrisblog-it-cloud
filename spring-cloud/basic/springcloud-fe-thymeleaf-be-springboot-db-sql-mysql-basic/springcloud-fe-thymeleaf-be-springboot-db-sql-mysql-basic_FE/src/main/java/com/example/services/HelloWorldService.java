package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.dtos.HelloWorldDto;

@Service
public class HelloWorldService {
    
    private RestClient restClient;

    @Autowired
    public HelloWorldService(RestClient restClient) {
        this.restClient = restClient;
    }

    public HelloWorldDto findById(Long id) {
        return restClient.get()
                .uri("/message/{id}", id)
                .retrieve()
                .body(HelloWorldDto.class);
    }

}
