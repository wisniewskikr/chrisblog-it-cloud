package com.example.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class KafkaService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.status}")
    private String topicStatus;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatus(String status) {
        kafkaTemplate.send(topicStatus, status);
    }

    @KafkaListener(topics = "#{'${topic.message}'}")
    public void messageListener(String message) throws InterruptedException {

        log.info("message: {}", message);

        Thread.sleep(3000);

        boolean isSuccess = new Random().nextBoolean();
        if (isSuccess) {
            sendStatus("SUCCESS");
        } else {
            sendStatus("FAILURE");
        }

    }

}
