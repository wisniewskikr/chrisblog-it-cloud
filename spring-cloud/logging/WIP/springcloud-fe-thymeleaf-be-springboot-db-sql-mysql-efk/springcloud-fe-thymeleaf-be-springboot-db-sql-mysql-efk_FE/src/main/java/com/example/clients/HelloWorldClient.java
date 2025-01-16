package com.example.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.dtos.HelloWorldDto;

@HttpExchange
public interface HelloWorldClient {

    @GetExchange("/message/{id}")
    HelloWorldDto findById(@PathVariable("id") Long id);

}