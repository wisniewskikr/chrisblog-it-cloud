package com.example.jsons;

public class HelloWorldJson {
	
	private String message;
	
	public HelloWorldJson() {}

	public HelloWorldJson(String message) {		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
