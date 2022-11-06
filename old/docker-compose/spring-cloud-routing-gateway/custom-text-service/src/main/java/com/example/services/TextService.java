package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.db.entities.TextEntity;
import com.example.db.repositories.TextRepository;

@Service
public class TextService {
	
	private TextRepository textRepository;

	@Autowired
	public TextService(TextRepository textRepository) {
		this.textRepository = textRepository;
	}
	
	public TextEntity findByLang(String lang) {
		return textRepository.findByLang(lang);
	}
	
	

}
