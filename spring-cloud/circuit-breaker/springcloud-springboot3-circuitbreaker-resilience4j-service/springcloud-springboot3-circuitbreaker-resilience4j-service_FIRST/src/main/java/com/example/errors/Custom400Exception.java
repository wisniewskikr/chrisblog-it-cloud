package com.example.errors;

public class Custom400Exception extends RuntimeException {

    public Custom400Exception(String message) {
        super(message);
    }

}
