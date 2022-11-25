package com.example.jsons;

public class HelloWorldStorageJson {
	
	private String message;
	private String uuid;
	
	public HelloWorldStorageJson() {}

	public HelloWorldStorageJson(String message, String uuid) {
		super();
		this.message = message;
		this.uuid = uuid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}		
	
}
