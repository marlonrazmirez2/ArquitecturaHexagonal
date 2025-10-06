package com.tecsup.example.hexagonal.domain.exception;

public class InvalidUserDataException extends RuntimeException{

    public InvalidUserDataException(String msg) {
        super("Invalid user data: " + msg);
    }
}
