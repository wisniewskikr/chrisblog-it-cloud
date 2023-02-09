package com.example.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dtos.BatchDto;

@FeignClient(name = "${service.batch.name}")
public interface BatchService {
	
	@GetMapping(value="/")
	public BatchDto getBatchDto();

}
