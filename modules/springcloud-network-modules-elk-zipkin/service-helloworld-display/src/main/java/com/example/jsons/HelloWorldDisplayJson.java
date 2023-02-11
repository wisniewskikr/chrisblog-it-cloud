package com.example.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloWorldDisplayJson {
	
	private String message;
	@JsonProperty(value = "port display")
	private String portDisplay;
	@JsonProperty(value = "port storage")
	private String portStorage;
	
	public HelloWorldDisplayJson() {}

	public HelloWorldDisplayJson(String message, String portDisplay, String portStorage) {
		this.message = message;
		this.portDisplay = portDisplay;
		this.portStorage = portStorage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPortDisplay() {
		return portDisplay;
	}

	public void setPortDisplay(String portDisplay) {
		this.portDisplay = portDisplay;
	}

	public String getPortStorage() {
		return portStorage;
	}

	public void setPortStorage(String portStorage) {
		this.portStorage = portStorage;
	}	
	
}
