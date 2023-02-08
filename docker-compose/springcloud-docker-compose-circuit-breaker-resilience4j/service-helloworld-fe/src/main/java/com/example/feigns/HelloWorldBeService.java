package com.example.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dtos.HelloWorldBeDto;

@FeignClient(name = "${service.helloworld.be.name}")
public interface HelloWorldBeService {
	
	@GetMapping(value="/")
	public HelloWorldBeDto getHelloWorldBeDto();

}
