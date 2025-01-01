package com.demo.First.Service;

import java.util.List;

import com.demo.First.Model.User;

public interface UserService {
    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(Long userID);
    public User getUser(Long userID);
    public List<User> getAllUser();
}
