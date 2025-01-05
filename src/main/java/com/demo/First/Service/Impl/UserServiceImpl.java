package com.demo.First.Service.Impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.demo.First.DTO.UserRequest;
import com.demo.First.DTO.UserResponseDTO;
import com.demo.First.Model.Marks;
import com.demo.First.Model.Subject;
import org.springframework.stereotype.Service;
import com.demo.First.Exception.EntryNotFoundException;
import com.demo.First.Model.User;
import com.demo.First.Repo.UserRepository;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(UserRequest user) {
        User newUser = User.builder()
                .userName(user.userName())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .role(User.Role.valueOf(user.role()))
                .dateOfBirth(user.dateOfBirth())
                .phoneNumber(user.phoneNumber())
                .address(user.address())
                .build();
        userRepository.save(newUser);
        return "Success";
    }

    @Override
    public String updateUser(User user) {
        User oldUser = userRepository.findById(user.getUserId()).orElse(null);
        if (oldUser != null) {
            Method[] methods = oldUser.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    String getterMethodName = "get" + method.getName().substring(3);
                    try {
                        Method getterMethod = user.getClass().getMethod(getterMethodName);
                        Object newValue = getterMethod.invoke(user);
                        if (newValue == null) {
                            Method getterOldMethod = oldUser.getClass().getMethod(getterMethodName);
                            Object oldValue = getterOldMethod.invoke(oldUser);
                            if (oldValue != null) {
                                method.invoke(user, oldValue);
                            }
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                        continue;
                    }
                }
            }
            userRepository.save(user);
            return "Success";
        } else {
            throw new EntryNotFoundException("User Not Found");
        }
    }

    @Override
    public String deleteUser(Long userID) {
        Optional<User>  user = userRepository.findById(userID);
        if (user.isEmpty()) {
            throw new EntryNotFoundException("User Not Found");
        }
        userRepository.deleteById(userID);
        return "Success";
    }

    @Override
    public UserResponseDTO getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new EntryNotFoundException("User Not Found");
        }
        UserResponseDTO userResponseDTO;
        User user = userOptional.get();
        if (user.getRole().name().equals("TEACHER")){
            List<String> subjectCodeList = user.getSubjects().stream()
                    .map(Subject::getSubjectName).toList();
            userResponseDTO = new UserResponseDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole().name(),
                    null,
                    subjectCodeList
            );
        }else {
            Map<String , Double> marks = new HashMap<>();
            for (Marks mark : user.getMarks()){
                marks.put(mark.getSubject().getSubjectName(),mark.getMarksObtained());
            }
            userResponseDTO = new UserResponseDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole().name(),
                    marks,
                    null
            );
        }
        return userResponseDTO;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User getUserObject(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new EntryNotFoundException("User Not Found");
        }
        return user.get();
    }
}
