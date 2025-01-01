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
public class MarksDTO {
    private Long marksId;
    private Long studentId;
    private Long subjectId;
    private Double marksObtained;
}
