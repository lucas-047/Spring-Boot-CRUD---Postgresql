package com.demo.First.Exception;

import org.springframework.http.HttpStatus;

public record CustomException(String message, HttpStatus httpStatus) {
}
