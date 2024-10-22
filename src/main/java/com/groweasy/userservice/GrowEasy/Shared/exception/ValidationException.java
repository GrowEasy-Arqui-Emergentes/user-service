package com.groweasy.userservice.GrowEasy.Shared.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
