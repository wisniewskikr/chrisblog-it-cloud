package com.example.jsons;

public class HelloWorldJson {
	
	private String message;
	private String secretMessage;
	private String applicationId;
	
	public HelloWorldJson() {}

	public HelloWorldJson(String message, String secretMessage, String applicationId) {		
		this.secretMessage = secretMessage;
		this.message = message;
		this.applicationId = applicationId;
	}
		
	public String getSecretMessage() {
		return secretMessage;
	}

	public void setSecretMessage(String secretMessage) {
		this.secretMessage = secretMessage;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}	
	
}
