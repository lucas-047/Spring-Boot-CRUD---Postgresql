package com.demo.First.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException {
    private final String message;
    private final HttpStatus httpStatus;
}
