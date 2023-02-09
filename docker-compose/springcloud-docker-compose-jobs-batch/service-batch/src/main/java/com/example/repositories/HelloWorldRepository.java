package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.HelloWorldEntity;

@Repository
public interface HelloWorldRepository extends CrudRepository<HelloWorldEntity, Long> {}
