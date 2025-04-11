package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldSecondDto;
import com.example.entities.HelloWorldEntity;
import com.example.repositories.HelloWorldRepository;

@Service
@AllArgsConstructor
public class HelloWorldService {

    private HelloWorldRepository helloWorldRepository;
    private Environment environment;

    public HelloWorldSecondDto findById(Long id) {
        
        HelloWorldEntity helloWorldEntity = helloWorldRepository.findById(id).orElseThrow(() -> new RuntimeException("Message doesn't exist"));
        String portSecond = environment.getProperty("local.server.port");
        return new HelloWorldSecondDto(helloWorldEntity.getId(), helloWorldEntity.getText(), portSecond);

    }

}