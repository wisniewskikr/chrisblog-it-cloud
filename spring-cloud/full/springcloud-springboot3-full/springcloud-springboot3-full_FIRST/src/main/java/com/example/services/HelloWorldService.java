package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clients.SecondClient;
import com.example.dtos.HelloWorldSecondDto;

@Service
public class HelloWorldService {
    
    private SecondClient secondClient;    

    @Autowired
    public HelloWorldService(SecondClient secondClient) {
        this.secondClient = secondClient;
    }

    public HelloWorldSecondDto findById(Long id) {
        return secondClient.findById(id);
    }

}
