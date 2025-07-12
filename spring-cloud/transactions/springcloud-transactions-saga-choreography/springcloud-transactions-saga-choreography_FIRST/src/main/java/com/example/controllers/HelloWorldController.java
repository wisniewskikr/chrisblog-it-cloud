package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.services.HelloWorldService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
public class HelloWorldController {

    @Value("${global.message}")
    private String message;

    private HelloWorldService helloWorldService;

    private MessageSseController messageSseController;

    private final List<SseEmitter> emittersStatus = new CopyOnWriteArrayList<>();

    public HelloWorldController(HelloWorldService helloWorldService, MessageSseController messageSseController) {
        this.helloWorldService = helloWorldService;
        this.messageSseController = messageSseController;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        helloWorldService.sendMessage(message);
        messageSseController.emitMessage(message);
        emitStatus("CREATED");
        return ResponseEntity.ok("Sent");

    }

    @GetMapping("/sse-status")
    public SseEmitter streamSseStatus() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emittersStatus.add(emitter);
        emitter.onCompletion(() -> emittersStatus.remove(emitter));
        emitter.onTimeout(() -> emittersStatus.remove(emitter));
        return emitter;
    }

    private void emitStatus(String status) {

        List<SseEmitter> deadEmittersStatus = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emittersStatus) {
            try {
                emitter.send(SseEmitter.event().data(status));
            } catch (IOException e) {
                deadEmittersStatus.add(emitter);
            }
        }
        emittersStatus.removeAll(deadEmittersStatus);

    }

}
