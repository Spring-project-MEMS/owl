package com.homework.first.exception;

public class InvalidPermissions extends RuntimeException{
    public InvalidPermissions() {
    }

    public InvalidPermissions(String message) {
        super(message);
    }

    public InvalidPermissions(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPermissions(Throwable cause) {
        super(cause);
    }
}
