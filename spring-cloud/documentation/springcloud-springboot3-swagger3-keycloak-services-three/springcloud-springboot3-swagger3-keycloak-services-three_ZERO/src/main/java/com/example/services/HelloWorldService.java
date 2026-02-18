package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.clients.FirstClient;
import com.example.dtos.HelloWorldFirstDto;
import com.example.dtos.HelloWorldSecondDto;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private FirstClient firstClient;
    private Environment environment;

    public HelloWorldFirstDto getPublicMessage() {
        HelloWorldSecondDto helloWorldSecondDto = firstClient.findById(1L);
        return new HelloWorldFirstDto(helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond());
    }

    public HelloWorldFirstDto getSecuredMessage() {
        HelloWorldSecondDto helloWorldSecondDto = firstClient.findById(2L);
        return new HelloWorldFirstDto(helloWorldSecondDto.text(), environment.getProperty("local.server.port"), helloWorldSecondDto.portSecond());
    }

}
