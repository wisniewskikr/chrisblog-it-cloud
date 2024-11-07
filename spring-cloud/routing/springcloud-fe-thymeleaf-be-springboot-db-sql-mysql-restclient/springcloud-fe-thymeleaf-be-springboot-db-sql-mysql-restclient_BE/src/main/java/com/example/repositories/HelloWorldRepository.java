package com.example.repositories;

import org.springframework.stereotype.Repository;

import com.example.entities.HelloWorldEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface HelloWorldRepository extends CrudRepository<HelloWorldEntity, Long> {}