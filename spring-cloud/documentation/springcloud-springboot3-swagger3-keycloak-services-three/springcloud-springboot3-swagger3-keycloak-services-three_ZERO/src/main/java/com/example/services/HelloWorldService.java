package com.example.services;

import com.example.dtos.HelloWorldZeroDto;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.clients.FirstClient;
import com.example.dtos.HelloWorldFirstDto;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private FirstClient firstClient;
    private Environment environment;

    public HelloWorldZeroDto getPublicMessage() {
        HelloWorldFirstDto helloWorldFirstDto = firstClient.publicHelloWorld().getBody();
        return new HelloWorldZeroDto(helloWorldFirstDto.text(), environment.getProperty("local.server.port"), helloWorldFirstDto.portFirst(), helloWorldFirstDto.portSecond());
    }

    public HelloWorldZeroDto getSecuredMessage() {
        HelloWorldFirstDto helloWorldFirstDto = firstClient.securedHelloWorld().getBody();
        return new HelloWorldZeroDto(helloWorldFirstDto.text(), environment.getProperty("local.server.port"), helloWorldFirstDto.portFirst(), helloWorldFirstDto.portSecond());
    }

}
