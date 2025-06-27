package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldSecondDto;

@Service
@AllArgsConstructor
public class HelloWorldService {

    private Environment environment;

    public HelloWorldSecondDto findById(Long id) {
        
        String text = (id == 1L) ? "Hello World, Public" : "Hello World, Secured";
        String portSecond = environment.getProperty("local.server.port");
        return new HelloWorldSecondDto(id, text, portSecond);

    }

}