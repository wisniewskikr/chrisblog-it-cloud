package com.example.services;

import com.example.models.Message;
import com.example.repositories.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message create(Message message) {

        Message existingMessage = null;

        if (message.getId() != null) {
            try {
                existingMessage = read(message.getId());
            } catch (Exception e) {}
        }

        if (existingMessage != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message with id " + existingMessage.getId() + " already exists");
        }

        return messageRepository.save(message);

    }

    public Message read(String id) {
        return messageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message with id " + id + " does not exist"));
    }

}
