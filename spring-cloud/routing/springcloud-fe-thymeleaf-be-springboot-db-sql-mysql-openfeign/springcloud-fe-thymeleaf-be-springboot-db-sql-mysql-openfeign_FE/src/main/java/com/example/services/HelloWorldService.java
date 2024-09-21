package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;
import com.example.feignclients.HelloWorldFeignClient;

@Service
public class HelloWorldService {
    
    private HelloWorldFeignClient helloWorldFeignClient;    

    @Autowired
    public HelloWorldService(HelloWorldFeignClient helloWorldFeignClient) {
        this.helloWorldFeignClient = helloWorldFeignClient;
    }

    public HelloWorldDto findById(Long id) {
        return helloWorldFeignClient.findById(id);
    }

}
