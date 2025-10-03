package com.example.clients;

import com.example.models.Message;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageClient {

    private final RestTemplate beRestTemplate;

    public MessageClient(RestTemplate beRestTemplate) {
        this.beRestTemplate = beRestTemplate;
    }

    public Message message() {
        return beRestTemplate.exchange("/api/message",
                        HttpMethod.GET, null, Message.class)
                .getBody();
    }

}