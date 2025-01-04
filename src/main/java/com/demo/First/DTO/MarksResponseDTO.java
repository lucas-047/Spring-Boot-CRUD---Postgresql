package com.demo.First.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public record MarksResponseDTO(Long marksId, Long studentId, Long subjectId, Double marksObtained) {
}
