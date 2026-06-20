package com.ca.userservice.exception;

public class NotUniqueUserNameException extends RuntimeException {
    public NotUniqueUserNameException(String message) {
        super(message);
    }
}
