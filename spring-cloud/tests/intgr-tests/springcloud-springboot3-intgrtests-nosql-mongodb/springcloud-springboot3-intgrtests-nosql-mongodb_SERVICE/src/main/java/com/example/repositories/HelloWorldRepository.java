package com.example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.models.HelloWorldDoc;

@Repository
public interface HelloWorldRepository extends MongoRepository<HelloWorldDoc, String> {}