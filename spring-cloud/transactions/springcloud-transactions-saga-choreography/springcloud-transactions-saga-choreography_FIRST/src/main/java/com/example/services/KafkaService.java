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

    @Value("${topic.status}")
    private String topicStatus;

    @Value("${topic.message}")
    private String topicMessage;

    @Value("${global.message}")
    private String message;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate,
                        MessageSseController messageSseController,
                        StatusSseController statusSseController) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageSseController = messageSseController;
        this.statusSseController = statusSseController;
    }

    public void sendStatus(String status) {
        kafkaTemplate.send(topicStatus, status);
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topicMessage, message);
    }

    @KafkaListener(topics = "#{'${topic.status}'}")
    public void statusListener(String status) {
        log.info("Status: {}", status);
        statusSseController.emitStatus(status);

        if ("FAILURE".equals(status)) {
            messageSseController.emitMessage(message + " (rollback)");
        }

    }

}
