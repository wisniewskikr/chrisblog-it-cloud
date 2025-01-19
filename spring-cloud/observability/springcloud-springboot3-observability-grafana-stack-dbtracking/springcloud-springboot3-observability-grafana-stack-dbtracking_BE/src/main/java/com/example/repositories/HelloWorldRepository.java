package com.example.repositories;

import org.springframework.stereotype.Repository;

import com.example.entities.HelloWorldEntity;

import io.micrometer.observation.annotation.Observed;

import org.springframework.data.repository.CrudRepository;

@Repository
@Observed
public interface HelloWorldRepository extends CrudRepository<HelloWorldEntity, Long> {}