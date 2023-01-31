package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.example.feigns")
public class ServiceHelloWorldDisplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHelloWorldDisplayApplication.class, args);
	}

}
