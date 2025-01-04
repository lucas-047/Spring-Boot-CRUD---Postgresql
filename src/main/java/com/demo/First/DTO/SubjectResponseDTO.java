package com.demo.First.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public record SubjectResponseDTO(Long subjectCode, String subjectName, Long teacherId) {
}
