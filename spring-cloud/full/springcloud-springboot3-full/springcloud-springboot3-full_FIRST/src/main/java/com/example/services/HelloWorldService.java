package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clients.BeClient;
import com.example.dtos.HelloWorldSecondDto;

@Service
public class HelloWorldService {
    
    private BeClient beClient;    

    @Autowired
    public HelloWorldService(BeClient beClient) {
        this.beClient = beClient;
    }

    public HelloWorldSecondDto findById(Long id) {
        return beClient.findById(id);
    }

}
