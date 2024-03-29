package com.example.entities;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HELLO_WORLDS")
public class HelloWorldEntity {
			
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(nullable = false)
    private String text;

	public HelloWorldEntity() {}
	
	public HelloWorldEntity(String text) {
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
