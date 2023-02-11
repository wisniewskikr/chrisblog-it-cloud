package com.example.jsons;

public class HelloWorldStorageJson {
	
	private String message;
	private String port;
	
	public HelloWorldStorageJson() {}

	public HelloWorldStorageJson(String message, String port) {		
		this.message = message;
		this.port = port;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}	
	
}
