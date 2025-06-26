package com.example.services;

import com.example.controllers.HelloWorldController;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloWorldServiceTest {

    private static final String TOPIC = "helloworld";
    private static final String MESSAGE = "Hello World Stranger";

    public static KafkaContainer kafkaContainer;

    private static KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private HelloWorldController helloWorldController;

    @DynamicPropertySource
    static void overrideKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("topic.name", () -> TOPIC);
    }

    @BeforeAll
    static void setupKafkaTemplate() {

        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));
        kafkaContainer.start();

        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);
        kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    @AfterAll
    static void tearDown() {
        kafkaContainer.stop();
    }

    @Test
    public void testKafkaConsumerReceivesMessage() throws InterruptedException {

        kafkaTemplate.send(TOPIC, MESSAGE);
        Thread.sleep(2000); // wait for consumer to process
        assertEquals(MESSAGE, helloWorldController.getMessage());

    }

}
