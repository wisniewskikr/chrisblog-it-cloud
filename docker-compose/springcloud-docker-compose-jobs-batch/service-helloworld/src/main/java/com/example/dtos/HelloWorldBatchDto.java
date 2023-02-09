package com.example.dtos;

public class HelloWorldBatchDto {
	
	private String message;
	
	public HelloWorldBatchDto() {}

	public HelloWorldBatchDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

}
