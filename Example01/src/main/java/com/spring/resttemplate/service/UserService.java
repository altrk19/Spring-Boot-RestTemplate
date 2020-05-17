package com.spring.resttemplate.service;

import com.spring.resttemplate.dto.User;

import java.util.List;

public interface UserService {
    User getSingleUser(String userId);
    User getSingleUserWithUriVariable(String userId);
    List<User> getAllUsers();
    List<User> getUserQueryParam(String id, String userName, String email);

    User createUser(User user);
    User createUser2(User user);

    void updateUser(String userId, User user);
    User updateUser2(String userId, User user);

    void deleteUser(String userId);
}
