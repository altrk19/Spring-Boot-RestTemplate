package com.spring.resttemplate.controller;

import com.spring.resttemplate.dto.User;
import com.spring.resttemplate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public User getSingleUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }
}
