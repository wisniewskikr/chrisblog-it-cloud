package com.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class MessageSseController {

    private final List<SseEmitter> emittersMessage = new CopyOnWriteArrayList<>();

    @GetMapping("/sse-message")
    public SseEmitter streamSseMessage() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emittersMessage.add(emitter);
        emitter.onCompletion(() -> emittersMessage.remove(emitter));
        emitter.onTimeout(() -> emittersMessage.remove(emitter));
        return emitter;
    }

    public void emitMessage(String msg) {

        List<SseEmitter> deadEmittersMessage = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emittersMessage) {
            try {
                emitter.send(SseEmitter.event().data(msg));
            } catch (IOException e) {
                deadEmittersMessage.add(emitter);
            }
        }
        emittersMessage.removeAll(deadEmittersMessage);

    }

}
