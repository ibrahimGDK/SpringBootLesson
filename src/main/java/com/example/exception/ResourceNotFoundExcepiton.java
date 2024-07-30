package com.example.exception;

public class ResourceNotFoundExcepiton extends RuntimeException{
    public ResourceNotFoundExcepiton(String message) {
        super(message);
    }
}
