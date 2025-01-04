package com.demo.First.Service.Impl;

import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;

import com.demo.First.DTO.SubjectRequest;
import com.demo.First.Exception.UserNotAuthorizedException;
import org.springframework.stereotype.Service;
import com.demo.First.Model.User;
import com.demo.First.DTO.SubjectResponseDTO;
import com.demo.First.Exception.DuplicateEntryException;
import com.demo.First.Exception.EntryNotFoundException;
import com.demo.First.Model.Subject;
import com.demo.First.Repo.SubjectRepository;
import com.demo.First.Service.SubjectService;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserService userService;

    @Override
    public String createSubject(SubjectRequest subject) {

        if (subjectRepository.existsBySubjectCode(subject.subjectCode())) {
            throw new DuplicateEntryException("Subject Code already exists");
        }
        User teacher = userService.getUserObject(subject.teacherId());
        if (teacher.getRole().name().equals("STUDENT")) {
            throw new UserNotAuthorizedException("Add a teacher as a subject teacher");
        }
        Subject newSubject = Subject.builder()
                .subjectCode(subject.subjectCode())
                .subjectName(subject.subjectName())
                .teacher(teacher)
                .build();
        subjectRepository.save(newSubject);
        return "Success";
    }

    @Override
    public String updateSubject(Subject subject) {
        Subject olSubject = subjectRepository.findById(subject.getSubjectId()).orElse(null);
        if (subject.getTeacher() != null) {
            User teacher = userService.getUserObject(subject.getTeacher().getUserId());
            subject.setTeacher(teacher);
        }
        if (olSubject != null) {
            Method[] methods = olSubject.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    String getterMethodName = "get" + method.getName().substring(3);
                    try {
                        Method getterMethod = subject.getClass().getMethod(getterMethodName);
                        Object newValue = getterMethod.invoke(subject);
                        if (newValue == null) {
                            Method getterOldMethod = olSubject.getClass().getMethod(getterMethodName);
                            Object oldValue = getterOldMethod.invoke(olSubject);
                            if (oldValue != null) {
                                method.invoke(subject, oldValue);
                            }
                        }
                    } catch (NoSuchMethodException | IllegalAccessException
                            | java.lang.reflect.InvocationTargetException e) {
                        continue;
                    }
                }
            }
            subjectRepository.save(subject);
            return "Success";
        } else {
            throw new EntryNotFoundException("Subject Not Found");

        }

    }

    @Override
    public String deleteSubject(Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isEmpty()) {
            throw new EntryNotFoundException("Subject Not Found");
        }
        subjectRepository.deleteById(subjectId);
        return "Success";
    }

    @Override
    public Subject getSubject(Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isEmpty()) {
            throw new EntryNotFoundException("Subject Not Found");
        }
        return subject.get();
    }

    @Override
    public SubjectResponseDTO getSubjectDTO(Long subjectID) {
        Subject subject = getSubject(subjectID);
        return new SubjectResponseDTO(subject.getSubjectId(), subject.getSubjectName(),
                subject.getTeacher() != null ? subject.getTeacher().getUserId() : null);
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjectDTO() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectResponseDTO> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            dtos.add(new SubjectResponseDTO(subject.getSubjectId(), subject.getSubjectName(),
                    subject.getTeacher() != null ? subject.getTeacher().getUserId() : null));
        }
        return dtos;
    }

}