package com.demo.First.Controller;

import java.util.List;

import com.demo.First.DTO.UserRequest;
import com.demo.First.DTO.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.demo.First.Model.User;
import com.demo.First.Response.ResponseHandler;
import com.demo.First.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createUser(@RequestBody UserRequest user){
        userService.createUser(user);
        return ResponseHandler.responseBuilder("User Created Successfully", HttpStatus.CREATED, null);
    }
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseHandler.responseBuilder("User Updated Successfully", HttpStatus.OK, null);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseHandler.responseBuilder("User Deleted Successfully", HttpStatus.OK, null);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId) {
        UserResponseDTO user = userService.getUser(userId);
        return ResponseHandler.responseBuilder("User fetched Successfully", HttpStatus.OK, user);
    }
    
    @GetMapping
    public ResponseEntity<Object> createUser(){
        List<User> users = userService.getAllUser();
        return ResponseHandler.responseBuilder("User list fetched Successfully", HttpStatus.OK, users);
    }
}
