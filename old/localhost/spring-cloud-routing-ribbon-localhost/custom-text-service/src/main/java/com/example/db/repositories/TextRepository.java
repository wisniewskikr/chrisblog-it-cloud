package com.example.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.db.entities.TextEntity;


public interface TextRepository extends JpaRepository<TextEntity, Long> {
	
	public TextEntity findByLang(String lang);
	
}
