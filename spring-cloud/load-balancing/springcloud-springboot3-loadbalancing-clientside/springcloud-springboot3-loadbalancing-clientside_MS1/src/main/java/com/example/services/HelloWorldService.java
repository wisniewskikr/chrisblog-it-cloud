package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clients.HelloWorldClient;
import com.example.dtos.HelloWorldDto;

@Service
public class HelloWorldService {
    
    private HelloWorldClient helloWorldClient;

    @Autowired
    public HelloWorldService(HelloWorldClient helloWorldClient) {
        this.helloWorldClient = helloWorldClient;
    }

    public HelloWorldDto findById(Long id) {
        return helloWorldClient.findById(id);
    }

}
