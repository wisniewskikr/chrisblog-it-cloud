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

    private final List<SseEmitter> emittersMessage = new CopyOnWriteArrayList<>();

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/sse-message")
    public SseEmitter streamSse() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emittersMessage.add(emitter);
        emitter.onCompletion(() -> emittersMessage.remove(emitter));
        emitter.onTimeout(() -> emittersMessage.remove(emitter));
        return emitter;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {

        helloWorldService.sendMessage(message);

        List<SseEmitter> deadEmittersMessage = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emittersMessage) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                deadEmittersMessage.add(emitter);
            }
        }
        emittersMessage.removeAll(deadEmittersMessage);

        return ResponseEntity.ok("Sent");
    }

}
