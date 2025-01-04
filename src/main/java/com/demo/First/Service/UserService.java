package com.demo.First.Service;

import java.util.List;

import com.demo.First.DTO.UserRequest;
import com.demo.First.DTO.UserResponseDTO;
import com.demo.First.Model.User;

public interface UserService {
    public String createUser(UserRequest user);
    public String updateUser(User user);
    public String deleteUser(Long userID);
    public UserResponseDTO getUser(Long userID);
    public List<User> getAllUser();
    public User getUserObject(Long userId);
}
