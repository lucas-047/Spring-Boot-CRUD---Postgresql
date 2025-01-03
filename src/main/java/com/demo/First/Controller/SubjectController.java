package com.demo.First.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.demo.First.DTO.SubjectDTO;
import com.demo.First.Model.Subject;
import com.demo.First.Response.ResponseHandler;
import com.demo.First.Service.SubjectService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createSubject(@RequestBody Subject subject){
        subjectService.createSubject(subject);
        return ResponseHandler.responseBuilder("Subject added Successfully", HttpStatus.CREATED, null);
    }
    @PutMapping
    public ResponseEntity<Object> updateSubject(@RequestBody Subject subject){
        subjectService.updateSubject(subject);
        return ResponseHandler.responseBuilder("Subject updated Successfully", HttpStatus.OK, null);
    }
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Object> deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseHandler.responseBuilder("Subject deleted Successfully", HttpStatus.OK, null);
    }
    @GetMapping("/{subjectId}")
    public ResponseEntity<Object> getSubject(@PathVariable Long subjectId) {
        SubjectDTO subject = subjectService.getSubjectDTO(subjectId);
        return ResponseHandler.responseBuilder("Subject fetched", HttpStatus.OK, subject);
    }
    
    @GetMapping
    public ResponseEntity<Object> createSubject(){
        List<SubjectDTO> subjects = subjectService.getAllSubjectDTO();
        return ResponseHandler.responseBuilder("Subject list fetched", HttpStatus.OK, subjects);
    }
}
