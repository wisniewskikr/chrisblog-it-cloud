package com.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class StatusSseController {

    private final List<SseEmitter> emittersStatus = new CopyOnWriteArrayList<>();

    @GetMapping("/sse-status")
    public SseEmitter streamSseStatus() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emittersStatus.add(emitter);
        emitter.onCompletion(() -> emittersStatus.remove(emitter));
        emitter.onTimeout(() -> emittersStatus.remove(emitter));
        return emitter;
    }

    public void emitStatus(String status) {

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
