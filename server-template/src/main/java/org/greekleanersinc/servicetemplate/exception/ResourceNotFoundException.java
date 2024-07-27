package org.greekleanersinc.servicetemplate.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        throw new RuntimeException(message);
    }
}
