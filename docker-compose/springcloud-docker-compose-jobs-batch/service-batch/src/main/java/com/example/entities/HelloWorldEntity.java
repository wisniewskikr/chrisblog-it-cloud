package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HELLO_WORLDS")
public class HelloWorldEntity {
			
	@Id
    private Long id;
 
    @Column(nullable = false)
    private String text;

	public HelloWorldEntity() {}
	
	public HelloWorldEntity(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
