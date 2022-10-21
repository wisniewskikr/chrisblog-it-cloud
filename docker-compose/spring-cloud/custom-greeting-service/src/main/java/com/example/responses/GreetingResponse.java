package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GreetingResponse {	
	
	private String greeting;
	@JsonProperty(value = "greeting port")
	private String greetingPort;
	@JsonProperty(value = "greeting version")
	private String greegingVersion;
	@JsonProperty(value = "text port")
	private String textPort;
	@JsonProperty(value = "text version")
	private String textVersion;
	
	public GreetingResponse(String greeting, String greetingPort, String greegingVersion, String textPort,
			String textVersion) {
		this.greeting = greeting;
		this.greetingPort = greetingPort;
		this.greegingVersion = greegingVersion;
		this.textPort = textPort;
		this.textVersion = textVersion;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getGreetingPort() {
		return greetingPort;
	}
	public void setGreetingPort(String greetingPort) {
		this.greetingPort = greetingPort;
	}
	public String getGreegingVersion() {
		return greegingVersion;
	}
	public void setGreegingVersion(String greegingVersion) {
		this.greegingVersion = greegingVersion;
	}
	public String getTextPort() {
		return textPort;
	}
	public void setTextPort(String textPort) {
		this.textPort = textPort;
	}
	public String getTextVersion() {
		return textVersion;
	}
	public void setTextVersion(String textVersion) {
		this.textVersion = textVersion;
	}
	
	
	

	
	
	
}