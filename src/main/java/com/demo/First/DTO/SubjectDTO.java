package com.demo.First.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SubjectDTO {
    private Long subjectId;
    private String subjectName;
    private Long teacherId;
}
