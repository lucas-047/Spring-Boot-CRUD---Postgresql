package com.demo.First.DTO;

import java.util.List;
import java.util.Map;

public record UserResponseDTO(Long userId, String userName, String firstName, String lastName, String email,
                              String role, Map<String,Double> marks, List<String> subjects) {

}
