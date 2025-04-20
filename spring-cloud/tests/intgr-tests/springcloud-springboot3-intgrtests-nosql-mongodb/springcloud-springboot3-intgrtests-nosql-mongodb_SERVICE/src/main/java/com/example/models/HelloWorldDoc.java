package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "hello-world")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloWorldDoc {

    private String id;
    private String text;

}