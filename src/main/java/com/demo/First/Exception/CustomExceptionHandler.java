package com.demo.First.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {EntryNotFoundException.class})
    public ResponseEntity<Object> handleEntryNotFoundException
            (EntryNotFoundException entryNotFoundException) {
        CustomException customException = new CustomException(
                entryNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateEntryException.class})
    public ResponseEntity<Object> handleDuplicateEntryException
            (DuplicateEntryException duplicateEntryException) {
        CustomException customException = new CustomException(
                duplicateEntryException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<Object> handleUserNotAuthorizedException
            (UserNotAuthorizedException userNotAuthorizedException) {
        CustomException customException = new CustomException(
                userNotAuthorizedException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }
}
