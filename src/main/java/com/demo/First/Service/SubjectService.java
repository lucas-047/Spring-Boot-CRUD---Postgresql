package com.demo.First.Service;

import java.util.List;

import com.demo.First.DTO.SubjectDTO;
import com.demo.First.Model.Subject;

public interface SubjectService {
    public String createSubject(Subject subject);
    public String updateSubject(Subject subject);
    public String deleteSubject(Long subjectID);
    public SubjectDTO getSubjectDTO(Long subjectId);
    public List<SubjectDTO> getAllSubjectDTO();
    public Subject getSubject(Long subjectId);
}
