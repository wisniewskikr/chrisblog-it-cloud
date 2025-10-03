package com.example.services;

import com.example.clients.MessageClient;
import com.example.models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageClient messageClient;

    public MessageService(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    public Message getMessage() {
        return messageClient.message();
    }
}
