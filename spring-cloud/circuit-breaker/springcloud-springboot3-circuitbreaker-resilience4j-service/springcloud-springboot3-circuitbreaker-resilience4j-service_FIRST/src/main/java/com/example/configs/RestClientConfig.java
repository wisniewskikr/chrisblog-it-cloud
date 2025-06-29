package com.example.configs;

import java.io.IOException;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;

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

        ClientHttpRequestInterceptor errorInterceptor = new ClientHttpRequestInterceptor() {

            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution) throws IOException {
                ClientHttpResponse response = execution.execute(request, body);
                if (response.getStatusCode().value() == 400) {
                    System.out.println("First service handles status 400");
                }
                return response;
            }

        };

        InterceptingClientHttpRequestFactory interceptingFactory =
                new InterceptingClientHttpRequestFactory(requestFactory, java.util.List.of(errorInterceptor));

        RestClient restClient = restClientBuilder
                .requestFactory(interceptingFactory)
                .baseUrl(apiUrl)
                .build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(SecondClient.class);
    }
}
