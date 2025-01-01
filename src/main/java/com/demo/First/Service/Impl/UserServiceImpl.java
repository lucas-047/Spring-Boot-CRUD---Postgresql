package com.demo.First.Service.Impl;

import java.lang.reflect.Method;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.First.Model.User;
import com.demo.First.Repo.UserRepository;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "User Register Sucessful";
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
            return "User Update Sucessful";
        } else {
            return "User not found";
        }
    }

    @Override
    public String deleteUser(Long userID) {
        userRepository.deleteById(userID);
        return "User Delete Sucessful";
    }

    @Override
    public User getUser(Long userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}