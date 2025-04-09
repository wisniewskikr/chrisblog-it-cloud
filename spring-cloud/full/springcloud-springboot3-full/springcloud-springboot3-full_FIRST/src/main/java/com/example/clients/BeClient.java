package com.example.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.dtos.HelloWorldSecondDto;

@HttpExchange
public interface BeClient {

    @GetExchange("/message/{id}")
    HelloWorldSecondDto findById(@PathVariable("id") Long id);

}