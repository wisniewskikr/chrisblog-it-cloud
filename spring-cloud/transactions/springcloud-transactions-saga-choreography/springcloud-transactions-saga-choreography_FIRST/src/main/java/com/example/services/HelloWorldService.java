package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.clients.SecondClient;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private SecondClient secondClient;  

    public void sendMessage(String message) {
        secondClient.helloWorld(message);
    }

}
