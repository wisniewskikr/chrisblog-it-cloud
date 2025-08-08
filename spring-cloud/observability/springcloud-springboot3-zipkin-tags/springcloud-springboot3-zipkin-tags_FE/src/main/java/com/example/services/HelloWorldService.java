package com.example.services;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clients.HelloWorldClient;
import com.example.dtos.HelloWorldDto;

@Service
public class HelloWorldService {
    
    private final HelloWorldClient helloWorldClient;
    private final Tracer tracer;

    @Autowired
    public HelloWorldService(HelloWorldClient helloWorldClient, Tracer tracer) {
        this.helloWorldClient = helloWorldClient;
        this.tracer = tracer;
    }

    public HelloWorldDto findById(Long id) {

        Span spanFirst = tracer.nextSpan().name("span-first").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            spanFirst.finish();
        }

        Span spanSecond = tracer.nextSpan().name("span-second").start();
        spanSecond.tag("id", id.toString());
        try {
            return helloWorldClient.findById(id);
        } finally {
            spanSecond.finish();
        }

    }

}
