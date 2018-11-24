package com.company.example.springbootseed.core.errorhandling.exceptions;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
// this feature was not used in order to avoid tight coupling of exceptions to a specific API technology. Look at the handler
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
