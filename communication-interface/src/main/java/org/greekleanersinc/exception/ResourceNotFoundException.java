package org.greekleanersinc.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        throw new RuntimeException(message);
    }
}

