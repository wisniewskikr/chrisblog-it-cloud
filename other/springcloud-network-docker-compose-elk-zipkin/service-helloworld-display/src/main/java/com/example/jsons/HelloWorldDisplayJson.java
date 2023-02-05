package com.example.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloWorldDisplayJson {
	
	private String message;
	@JsonProperty(value = "uuid display")
	private String uuidDisplay;
	@JsonProperty(value = "uuid storage")
	private String uuidStorage;
	
	public HelloWorldDisplayJson() {}

	public HelloWorldDisplayJson(String message, String uuidDisplay, String uuidStorage) {
		this.message = message;
		this.uuidDisplay = uuidDisplay;
		this.uuidStorage = uuidStorage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUuidDisplay() {
		return uuidDisplay;
	}

	public void setUuidDisplay(String uuidDisplay) {
		this.uuidDisplay = uuidDisplay;
	}

	public String getUuidStorage() {
		return uuidStorage;
	}

	public void setUuidStorage(String uuidStorage) {
		this.uuidStorage = uuidStorage;
	}

	
	
}
