package com.example.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.clients.SecondClient;

@Service
@AllArgsConstructor
public class HelloWorldService {
    
    private SecondClient secondClient;

    public String getStatus200Message() {
        return secondClient.status200().getBody();
    }

    public String getStatus400Message() { return secondClient.status400().getBody(); }

    public String getStatus500Message() {
        return secondClient.status500().getBody();
    }

    public String getTimeoutMessage() {
        return secondClient.statusTimeout().getBody();
    }

}
