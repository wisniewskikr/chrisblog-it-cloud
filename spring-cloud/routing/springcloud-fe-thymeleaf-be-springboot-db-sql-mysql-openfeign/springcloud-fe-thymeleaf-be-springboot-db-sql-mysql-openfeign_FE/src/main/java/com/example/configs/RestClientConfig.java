package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${baseurl.be}")
    private String baseUrlBe;
    
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(baseUrlBe)
                .build();
    }

}