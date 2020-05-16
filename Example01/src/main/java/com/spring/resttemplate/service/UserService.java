package com.spring.resttemplate.service;

import com.spring.resttemplate.dto.User;

import java.util.List;

public interface UserService {
    User getUser(String userId);
    List<User> getAllUsers();
}
