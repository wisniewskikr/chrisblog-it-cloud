package com.example.models;

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