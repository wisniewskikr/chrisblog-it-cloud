package com.example.controllers;

import com.example.models.Message;
import com.example.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    public final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<Message> message() {

        logger.info("Called BE method MessageController.message()");

        Message message = messageService.getMessage();

        return ResponseEntity.ok(message);

    }

}
