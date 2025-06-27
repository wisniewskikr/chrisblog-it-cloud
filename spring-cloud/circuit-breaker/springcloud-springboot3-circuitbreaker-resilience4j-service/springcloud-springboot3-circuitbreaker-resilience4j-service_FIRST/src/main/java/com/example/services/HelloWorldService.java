package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.clients.SecondClient;
import com.example.dtos.HelloWorldFirstDto;
import com.example.dtos.HelloWorldSecondDto;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private SecondClient secondClient;  
    private Environment environment;

    public HelloWorldFirstDto getTimeoutMessage() {
        HelloWorldSecondDto helloWorldSecondDto = secondClient.findById(1L);
        return new HelloWorldFirstDto(helloWorldSecondDto.text());
    }

}
