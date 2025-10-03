package com.example.services;

import com.example.models.Message;
import com.example.repositories.MessageRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ObservationRegistry observationRegistry;

    public MessageService(MessageRepository messageRepository, ObservationRegistry observationRegistry) {
        this.messageRepository = messageRepository;
        this.observationRegistry = observationRegistry;
    }

    public Message create(Message message) {

        Message existingMessage = null;

        if (message.getId() != null) {
            try {
                existingMessage = read(message.getId());
            } catch (Exception e) {
                // swallow since we want to create new
            }
        }

        if (existingMessage != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Message with id " + existingMessage.getId() + " already exists");
        }

        return Observation.createNotStarted("mongo.message.create", observationRegistry)
                .contextualName("mongo message.create") // span name in Tempo
                .observe(() -> messageRepository.save(message));
    }

    public Message read(String id) {
        return Observation.createNotStarted("mongo.message.read", observationRegistry)
                .contextualName("mongo message.read") // span name in Tempo
                .observe(() -> messageRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Message with id " + id + " does not exist")));
    }
}