package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.clients.SecondClient;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private SecondClient secondClient;

    public String get200Message() {
        return secondClient.status200().getBody();
    }

    public String getTimeoutMessage() {
        return secondClient.statusTimeout().getBody();
    }

}
