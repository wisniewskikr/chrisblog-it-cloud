package com.example.responses;


public class TextResponse {	
	
	private String text;
	private String port;
	private String version;
	
	public TextResponse(String text, String port, String version) {
		this.text = text;
		this.port = port;
		this.version = version;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}	
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}