package com.example.services;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.clients.SecondClient;
import com.example.dtos.HelloWorldFirstDto;
import com.example.dtos.HelloWorldSecondDto;

@Service
public class HelloWorldService {
    
    private SecondClient secondClient;  
    private Environment environment;   

    public HelloWorldService(SecondClient secondClient, Environment environment) {
        this.secondClient = secondClient;
        this.environment = environment;
    }

    public HelloWorldFirstDto getPublicMessage() {
        HelloWorldSecondDto helloWorldSecondDto = secondClient.findById(1L);
        return new HelloWorldFirstDto(helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond());
    }

    public HelloWorldFirstDto getSecuredMessage() {
        HelloWorldSecondDto helloWorldSecondDto = secondClient.findById(2L);
        return new HelloWorldFirstDto(helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond());
    }

}
