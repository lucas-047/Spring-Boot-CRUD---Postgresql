package com.demo.First.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.First.Model.User;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/user")
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
    
    @GetMapping
    public List<User> createUser(){
        return userService.getAllUser();
    }
}
