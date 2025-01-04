package com.demo.First.Service;

import java.util.List;

import com.demo.First.DTO.SubjectResponseDTO;
import com.demo.First.DTO.SubjectRequest;
import com.demo.First.Model.Subject;

public interface SubjectService {
    public String createSubject(SubjectRequest subject);
    public String updateSubject(Subject subject);
    public String deleteSubject(Long subjectID);
    public SubjectResponseDTO getSubjectDTO(Long subjectId);
    public List<SubjectResponseDTO> getAllSubjectDTO();
    public Subject getSubject(Long subjectId);
}
