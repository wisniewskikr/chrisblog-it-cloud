package com.example.services;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092",
                "port=9092"
        }
)
public class HelloWorldServiceTest {

    private static final String TOPIC_NAME = "helloworld";
    private static final String MESSAGE = "Hello World Stranger";

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void testSendMessage() {
        // Send message
        String response = helloWorldService.sendMessage(MESSAGE);

        assertThat(response).isEqualTo("The message was sent to Consumer via Kafka");

        // Set up test Kafka consumer manually
        Map<String, Object> consumerProps = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.GROUP_ID_CONFIG, "testGroup",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName(),
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"
        );

        try (var consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(
                consumerProps)) {

            consumer.subscribe(java.util.Collections.singleton(TOPIC_NAME));
            ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, TOPIC_NAME);

            assertThat(record.value()).isEqualTo(MESSAGE);
        }

    }

}