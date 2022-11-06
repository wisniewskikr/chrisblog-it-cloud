package com.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.responses.TextResponse;

@FeignClient(name="custom-text-service", url="localhost:9090")
public interface CustomTextServiceClient {
	
	@GetMapping(value="/text/lang/{lang}")
	public TextResponse provideText(@PathVariable(value = "lang") String lang);

}
