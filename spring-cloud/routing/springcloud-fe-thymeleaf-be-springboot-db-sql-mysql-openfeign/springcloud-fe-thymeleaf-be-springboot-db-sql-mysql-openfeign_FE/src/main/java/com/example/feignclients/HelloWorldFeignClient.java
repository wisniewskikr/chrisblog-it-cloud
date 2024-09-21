package com.example.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dtos.HelloWorldDto;

@FeignClient(name = "${openfeign.client.name}", url = "${openfeing.api.url}")
public interface HelloWorldFeignClient {

    @GetMapping("/message/{id}")
    HelloWorldDto findById(@PathVariable("id") Long id);

}