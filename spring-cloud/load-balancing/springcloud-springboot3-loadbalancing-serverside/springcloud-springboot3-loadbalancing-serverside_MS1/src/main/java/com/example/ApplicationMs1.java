package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationMs1 {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMs1.class, args);
	}

}
