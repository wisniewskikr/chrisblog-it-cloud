package com.example.clients;

import com.example.models.ResponseMs2;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ClientMs2 {

    @GetExchange
    ResponseMs2 helloWorld();

}