package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.HelloWorldDto;
import com.example.entities.HelloWorldEntity;
import com.example.repositories.HelloWorldRepository;

@Service
public class HelloWorldService {
	
	private HelloWorldRepository helloWorldRepository;

	@Autowired
	public HelloWorldService(HelloWorldRepository helloWorldRepository) {
		this.helloWorldRepository = helloWorldRepository;
	}
	
	public HelloWorldDto saveText(String text) {
		HelloWorldEntity entity = new HelloWorldEntity();
		entity.setText(text);
		return new HelloWorldDto(helloWorldRepository.save(entity));
	}
	
	public String readText(Long id) {
		return helloWorldRepository.findById(id).get().getText();
	}

}
