package com.example.configs;

import java.time.Duration;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.clients.SecondClient;

@Configuration
public class RestClientConfig {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.timeout.ms}")
    private int timeoutInMillis;

    @Bean
    public SecondClient beClient(RestClient.Builder restClientBuilder) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(timeoutInMillis))
                .setConnectTimeout(Timeout.ofMilliseconds(timeoutInMillis))
                .setResponseTimeout(Timeout.ofMilliseconds(timeoutInMillis))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestClient restClient = restClientBuilder
                .requestFactory(requestFactory)
                .baseUrl(apiUrl)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(SecondClient.class);

    }

}
