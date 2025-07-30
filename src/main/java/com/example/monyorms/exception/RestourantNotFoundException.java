package com.example.monyorms.exception;

public class RestourantNotFoundException extends RuntimeException {
    public RestourantNotFoundException(String message) {
        super(message);
    }
}
