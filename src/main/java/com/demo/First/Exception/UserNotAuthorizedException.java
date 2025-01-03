package com.demo.First.Exception;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
