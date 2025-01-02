package com.demo.First.Service.Impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.First.Model.Marks;
import com.demo.First.Model.Subject;
import com.demo.First.Model.User;
import com.demo.First.DTO.MarksDTO;
import com.demo.First.Exception.EntryNotFoundException;
import com.demo.First.Repo.MarksRepository;
import com.demo.First.Service.MarksService;
import com.demo.First.Service.SubjectService;
import com.demo.First.Service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MarksServiceImpl implements MarksService {
    @Autowired
    MarksRepository marksRepository;
    @Autowired
    UserService userService;
    @Autowired
    SubjectService subjectService;

    @Override
    public String createMarks(Marks marks) {

        User student = userService.getUser(marks.getStudent().getUserId());
        if (student == null) {
            return "No Student Added";
        }
        marks.setStudent(student);
        Subject subject = subjectService.getSubject(marks.getSubject().getSubjectId());
        if (subject == null) {
            return "No Subject Added";
        }
        marks.setSubject(subject);
        marksRepository.save(marks);
        return "Success";
    }

    @Override
    public String updateMarks(Marks marks) {
        Marks oldMarks = marksRepository.findById(marks.getMarksId()).orElse(null);
        if (marks.getStudent() != null) {
            User student = userService.getUser(marks.getStudent().getUserId());
            marks.setStudent(student);
        }
        if (marks.getSubject() != null) {
            Subject subject = subjectService.getSubject(marks.getSubject().getSubjectId());
            marks.setSubject(subject);
        }
        if (oldMarks != null) {
            Method[] methods = oldMarks.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    String getterMethodName = "get" + method.getName().substring(3);
                    try {
                        Method getterMethod = marks.getClass().getMethod(getterMethodName);
                        Object newValue = getterMethod.invoke(marks);
                        if (newValue == null) {
                            Method getterOldMethod = oldMarks.getClass().getMethod(getterMethodName);
                            Object oldValue = getterOldMethod.invoke(oldMarks);
                            if (oldValue != null) {
                                method.invoke(marks, oldValue);
                            }
                        }
                    } catch (NoSuchMethodException | IllegalAccessException
                            | java.lang.reflect.InvocationTargetException e) {
                        continue;
                    }
                }
            }
            marksRepository.save(marks);
            return "Success";
        } else {
            throw new EntryNotFoundException("Marks Not Found");

        }
    }

    @Override
    public String deleteMarks(Long marksID) {
        marksRepository.deleteById(marksID);
        return "Success";
    }

    @Override
    public Marks getMarks(Long marksId) {
        Optional<Marks> marks = marksRepository.findById(marksId);
        if (!marks.isPresent()) {
            throw new EntryNotFoundException("Marks not Found");
        }
        return marks.get();

    }

    @Override
    public MarksDTO getMarksDTO(Long marksID) {
        Marks marks = getMarks(marksID);
        return new MarksDTO(marks.getMarksId(), marks.getStudent() != null ? marks.getStudent().getUserId() : null,
                marks.getSubject() != null ? marks.getSubject().getSubjectId() : null, marks.getMarksObtained());
    }

    @Override
    public List<MarksDTO> getAllMarksDTO() {
        List<Marks> marks = marksRepository.findAll();
        List<MarksDTO> dtos = new ArrayList<>();
        for (Marks mark : marks) {
            dtos.add(new MarksDTO(mark.getMarksId(), mark.getStudent() != null ? mark.getStudent().getUserId() : null,
                    mark.getSubject() != null ? mark.getSubject().getSubjectId() : null, mark.getMarksObtained()));
        }
        return dtos;
    }

}