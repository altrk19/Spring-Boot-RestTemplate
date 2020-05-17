package com.spring.resttemplate.controller;

import com.spring.resttemplate.dto.User;
import com.spring.resttemplate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public User getSingleUser(@PathVariable("userId") String userId){
        return userService.getSingleUser(userId);
    }

    @GetMapping("/user/v1/{userId}")
    public User getSingleUserWithUrlVariables(@PathVariable("userId") String userId) {
        return userService.getSingleUserWithUriVariable(userId);
    }

    @GetMapping("/users/v2")
    public List<User> getAllUsersWitQueryParam(@RequestParam(value = "id", required = false) String id,
                                               @RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "email", required = false) String email) {
        return userService.getUserQueryParam(id, username, email);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/v3/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser2(@RequestBody User user) {
        return userService.createUser2(user);
    }

    @PutMapping("/user/v4/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("userId") String userId,
                           @RequestBody User user) {
        userService.updateUser(userId, user);
    }

    @PutMapping("/user/v5/{userId}")
    public User updateUser2(@PathVariable("userId") String userId,
                           @RequestBody User user) {
        return userService.updateUser2(userId, user);
    }

    @DeleteMapping("/user/v6/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }
}
