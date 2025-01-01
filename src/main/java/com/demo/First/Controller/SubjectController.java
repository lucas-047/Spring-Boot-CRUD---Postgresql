package com.demo.First.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.First.DTO.SubjectDTO;
import com.demo.First.Model.Subject;
import com.demo.First.Service.SubjectService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/subject")
@NoArgsConstructor
@AllArgsConstructor
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @PostMapping
    public String createSubject(@RequestBody Subject subject){
        return subjectService.createSubject(subject);
    }
    @PutMapping
    public String updateSubject(@RequestBody Subject subject){
        return subjectService.updateSubject(subject);
    }
    @DeleteMapping("/{subjectId}")
    public String deleteSubject(@PathVariable Long subjectId){
        return subjectService.deleteSubject(subjectId);
    }
    @GetMapping("/{subjectId}")
    public SubjectDTO getSubject(@PathVariable Long subjectId) {
        return subjectService.getSubjectDTO(subjectId);
    }
    
    @GetMapping
    public List<SubjectDTO> createSubject(){
        return subjectService.getAllSubjectDTO();
    }
}
