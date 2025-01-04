package com.demo.First.DTO;

import java.time.LocalDate;

public record UserRequest(String userName, String firstName, String lastName, String email, String role, LocalDate dateOfBirth, String phoneNumber, String address) {
}
