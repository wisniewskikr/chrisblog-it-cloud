package com.example.dtos;

import com.example.entities.HelloWorldEntity;

public class HelloWorldDto {
		
    private Long id; 
    private String text;    

	public HelloWorldDto(HelloWorldEntity entity) {
		this.id = entity.getId();
		this.text = entity.getText();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
