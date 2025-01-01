package com.demo.First.Service.Impl;

import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.First.Model.User;
import com.demo.First.DTO.SubjectDTO;
import com.demo.First.Model.Subject;
import com.demo.First.Repo.SubjectRepository;
import com.demo.First.Service.SubjectService;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UserService userService;

    @Override
    public String createSubject(Subject subject) {
        User teacher = userService.getUser(subject.getTeacher().getUserId());
        if (teacher == null) {
            return "No Teacher Added";
        }
        subject.setTeacher(teacher);
        subjectRepository.save(subject);
        return "Subject Register Sucessful";
    }

    @Override
    public String updateSubject(Subject subject) {
        Subject olSubject = subjectRepository.findById(subject.getSubjectId()).orElse(null);
        if (subject.getTeacher() != null) {
            User teacher = userService.getUser(subject.getTeacher().getUserId());
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
            return "Subject Update Sucessful";
        } else {
            return "No Subject Found";
        }

    }

    @Override
    public String deleteSubject(Long subjectID) {
        subjectRepository.deleteById(subjectID);
        return "Subject Delete Sucessful";
    }

    @Override
    public SubjectDTO getSubjectDTO(Long subjectID) {
        Subject subject = subjectRepository.findById(subjectID).get();
        return new SubjectDTO(subject.getSubjectId(), subject.getSubjectName(),
                subject.getTeacher() != null ? subject.getTeacher().getUserId() : null);
    }

    @Override
    public List<SubjectDTO> getAllSubjectDTO() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            dtos.add(new SubjectDTO(subject.getSubjectId(), subject.getSubjectName(),
                    subject.getTeacher() != null ? subject.getTeacher().getUserId() : null));
        }
        return dtos;
    }

    @Override
    public Subject getSubject(Long SubjectId){
        return subjectRepository.findById(SubjectId).get();
    }
}