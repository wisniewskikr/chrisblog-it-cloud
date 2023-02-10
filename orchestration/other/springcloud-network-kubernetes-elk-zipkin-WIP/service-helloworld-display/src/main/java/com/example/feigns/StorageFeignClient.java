package com.example.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jsons.HelloWorldStorageJson;

@FeignClient(name = "${service.helloworld.storage.name}")
public interface StorageFeignClient {
	
	@GetMapping(value="/")
	public HelloWorldStorageJson getHelloWorldFromStorage();

}
