package com.example.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloWorldSubscriberDto {
	
	private String message;
	@JsonProperty(value = "port of service BE")
	private String portBe;
	@JsonProperty(value = "uuid of service BE")
	private String uuidBe;
	@JsonProperty(value = "port of service FE")
	private String portFe;
	@JsonProperty(value = "uuid of service FE")
	private String uuidFe;
	
	public HelloWorldSubscriberDto() {}

	public HelloWorldSubscriberDto(String message, String portBe, String uuidBe, String portFe, String uuidFe) {
		this.message = message;
		this.portBe = portBe;
		this.uuidBe = uuidBe;
		this.portFe = portFe;
		this.uuidFe = uuidFe;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPortBe() {
		return portBe;
	}

	public void setPortBe(String portBe) {
		this.portBe = portBe;
	}

	public String getUuidBe() {
		return uuidBe;
	}

	public void setUuidBe(String uuidBe) {
		this.uuidBe = uuidBe;
	}

	public String getPortFe() {
		return portFe;
	}

	public void setPortFe(String portFe) {
		this.portFe = portFe;
	}

	public String getUuidFe() {
		return uuidFe;
	}

	public void setUuidFe(String uuidFe) {
		this.uuidFe = uuidFe;
	}
	
	
	
}
