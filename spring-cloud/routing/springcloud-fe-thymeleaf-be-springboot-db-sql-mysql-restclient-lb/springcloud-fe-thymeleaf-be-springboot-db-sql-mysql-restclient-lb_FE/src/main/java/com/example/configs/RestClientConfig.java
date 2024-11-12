package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.clients.HelloWorldClient;

@Configuration
public class RestClientConfig {

    @Value("${baseurl.be}")
    private String baseUrlBe;
    
    @Bean
    public HelloWorldClient helloWorldClient() {

        RestClient restClient = RestClient.builder().baseUrl(baseUrlBe).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(HelloWorldClient.class);

    }

}