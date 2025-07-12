package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatus(String status) {
        kafkaTemplate.send(topicName, status);
    }

}
