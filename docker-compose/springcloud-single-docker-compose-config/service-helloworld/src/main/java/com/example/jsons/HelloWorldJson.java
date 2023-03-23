package com.example.jsons;

public class HelloWorldJson {
	
	private String message;
	private String applicationId;
	
	public HelloWorldJson() {}

	public HelloWorldJson(String message, String applicationId) {		
		this.message = message;
		this.applicationId = applicationId;
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
