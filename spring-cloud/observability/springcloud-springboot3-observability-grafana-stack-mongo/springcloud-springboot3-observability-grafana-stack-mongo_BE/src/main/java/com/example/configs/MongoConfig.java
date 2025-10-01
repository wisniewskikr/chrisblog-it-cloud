package com.example.configs;

import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);
    private final ObservationRegistry observationRegistry;

    public MongoConfig(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
        logger.info("MongoConfig initialized with ObservationRegistry");
    }

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoObservationCustomizer() {
        logger.info("Registering MongoObservationCommandListener for tracing MongoDB operations");
        return (builder) -> builder.addCommandListener(
                new org.springframework.data.mongodb.observability.MongoObservationCommandListener(observationRegistry)
        );
    }
}