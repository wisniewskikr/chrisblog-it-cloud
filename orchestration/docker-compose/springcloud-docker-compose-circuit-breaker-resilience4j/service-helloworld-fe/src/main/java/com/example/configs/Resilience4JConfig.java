package com.example.configs;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {
	
	@Value("${circuitbreaker.sliding.window.size}")
    private int slidingWindowSize;
	
	@Value("${circuitbreaker.minimum.number.calls}")
    private int minimumNumberOfCalls;
	
	@Value("${circuitbreaker.failure.rate.threshold}")
    private int failureRateThreshold;
	
	@Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build())
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(slidingWindowSize)
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                        .minimumNumberOfCalls(minimumNumberOfCalls)
                        .failureRateThreshold(failureRateThreshold)
                        .build())
                .build());
    }
}
