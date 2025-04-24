package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${baseurl.second}")
    private String baseUrlSecond;
    
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(baseUrlSecond)
                .build();
    }

}