package com.example.configs;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import com.example.clients.HelloWorldClient;

@Configuration
public class RestClientConfig {

    private final Tracer tracer;

    public RestClientConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Value("${api.url}")
    private String apiUrl;

    @Bean
    public RestClient.Builder getRestClient() {
        return RestClient.builder();
    }
    
    @Bean
    public HelloWorldClient helloWorldClient() {

        RestClient restClient = getRestClient()
                .baseUrl(apiUrl)
                .requestInterceptor((request, body, execution) ->
                        {
                            Span span = tracer.currentSpan();
                            request.getHeaders().add("X-B3-TraceId", span.context().traceIdString());
                            request.getHeaders().add("X-B3-SpanId", span.context().spanIdString());
                            request.getHeaders().add("Sampled", "1");
                            return execution.execute(request, body);
                        }

                ).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory
                = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(HelloWorldClient.class);

    }

}