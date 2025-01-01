package com.demo.First.Exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message) {
        super(message);
    }
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
