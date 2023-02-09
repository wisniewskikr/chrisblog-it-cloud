package com.example.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dtos.HelloWorldBatchDto;

@FeignClient(name = "${service.helloworld.batch.name}")
public interface HelloWorldBatchService {
	
	@GetMapping(value="/")
	public HelloWorldBatchDto getHelloWorldBatchDto();

}
