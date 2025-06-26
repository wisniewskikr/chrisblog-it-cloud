package com.example.services;

import com.example.controllers.HelloWorldController;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EmbeddedKafka (
        partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092",
                "port=9092"
        }
)
public class HelloWorldServiceTest {

    private static final String TOPIC = "helloworld";
    private static final String MESSAGE = "Hello World Stranger";

    private static KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private HelloWorldController helloWorldController;

    @BeforeAll
    static void setupKafkaTemplate() {

        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);
        kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    @Test
    public void testKafkaConsumerReceivesMessage() throws InterruptedException {

        kafkaTemplate.send(TOPIC, MESSAGE);
        Thread.sleep(2000); // wait for consumer to process
        assertEquals(MESSAGE, helloWorldController.getMessage());

    }

}
