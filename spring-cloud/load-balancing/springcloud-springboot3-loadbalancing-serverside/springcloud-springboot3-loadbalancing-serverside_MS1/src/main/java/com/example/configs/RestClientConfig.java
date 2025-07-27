package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.clients.ClientMs2;

@Configuration
public class RestClientConfig {

    @Value("${baseurl.ms2}")
    private String baseUrlMs2;

    @Bean
    @LoadBalanced
    public RestClient.Builder getRestClient() {
        return RestClient.builder();
    }
    
    @Bean
    public ClientMs2 helloWorldClient() {

        RestClient restClient = getRestClient()
                .baseUrl(baseUrlMs2)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory
                = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ClientMs2.class);

    }

}