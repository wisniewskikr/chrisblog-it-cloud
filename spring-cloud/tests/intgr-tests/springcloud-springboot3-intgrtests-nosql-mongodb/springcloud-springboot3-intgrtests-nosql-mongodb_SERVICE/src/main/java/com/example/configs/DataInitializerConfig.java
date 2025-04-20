package com.example.configs;

import com.example.models.HelloWorldDoc;
import com.example.repositories.HelloWorldRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializerConfig {

    @Bean
    CommandLineRunner initData(HelloWorldRepository helloWorldRepository) {

        return args -> {
            helloWorldRepository.deleteAll(); // Clear existing data

            helloWorldRepository.save(new HelloWorldDoc("1", "Hello World, Public!"));
            helloWorldRepository.save(new HelloWorldDoc("2", "Hello World, Secured!"));

            System.out.println("Data initialized");
        };

    }

}
