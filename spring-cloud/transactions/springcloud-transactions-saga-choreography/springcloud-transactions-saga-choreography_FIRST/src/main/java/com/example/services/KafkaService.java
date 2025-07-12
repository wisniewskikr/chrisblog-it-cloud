package com.example.services;

import com.example.controllers.MessageSseController;
import com.example.controllers.StatusSseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

    private KafkaTemplate<String, String> kafkaTemplate;

    private MessageSseController messageSseController;

    private StatusSseController statusSseController;

    @Value("${topic.name}")
    private String topicName;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate,
                        MessageSseController messageSseController,
                        StatusSseController statusSseController) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageSseController = messageSseController;
        this.statusSseController = statusSseController;
    }

    public void sendStatus(String status) {
        kafkaTemplate.send(topicName, status);
    }

    @KafkaListener(topics = "#{'${topic.name}'}")
    public void statusListener(String status) {
        log.info("Status: {}", status);
        statusSseController.emitStatus(status);
    }

}
