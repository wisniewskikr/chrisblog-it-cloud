package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;
import com.example.models.HelloWorldDoc;
import com.example.repositories.HelloWorldRepository;

@Service
@AllArgsConstructor
public class HelloWorldService {

    private HelloWorldRepository helloWorldRepository;
    private Environment environment;

    public HelloWorldDto findById(String id) {
        
        HelloWorldDoc helloWorldDoc = helloWorldRepository.findById(id).orElseThrow(() -> new RuntimeException("Message doesn't exist"));
        String portSecond = environment.getProperty("local.server.port");
        return new HelloWorldDto(helloWorldDoc.getId(), helloWorldDoc.getText(), portSecond);

    }

}