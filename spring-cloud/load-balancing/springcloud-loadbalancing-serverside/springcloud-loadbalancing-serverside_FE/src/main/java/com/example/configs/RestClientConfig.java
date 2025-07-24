package com.example.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.clients.HelloWorldClient;

@Configuration
public class RestClientConfig {

    // TODO
    @Value("${baseurl.be}")
    private String baseUrlBe;

    @Bean
    @LoadBalanced
    public RestClient.Builder getRestClient() {
        return RestClient.builder();
    }
    
    @Bean
    public HelloWorldClient helloWorldClient() {

        RestClient restClient = getRestClient()
                .baseUrl("http://BE")
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory
                = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(HelloWorldClient.class);

    }

}