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

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

//    @GetMapping
//    public ResponseEntity<String> helloWorld() {
//
//        helloWorldService.sendMessage(message);
//
//        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
//        for (SseEmitter emitter : emitters) {
//            try {
//                emitter.send(SseEmitter.event().data(message));
//            } catch (IOException e) {
//                deadEmitters.add(emitter);
//            }
//        }
//        emitters.removeAll(deadEmitters);
//        return ResponseEntity.ok("Sent");
//    }

    @GetMapping("/sse")
    public SseEmitter streamSse() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage() {
        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data("Hello World"));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
        return ResponseEntity.ok("Sent");
    }

}
