package org.greekleanersinc.servicetemplate.exception;

public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(String message) {
        throw new RuntimeException(message);
    }
}
