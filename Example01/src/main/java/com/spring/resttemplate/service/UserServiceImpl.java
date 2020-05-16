package com.spring.resttemplate.service;

import com.spring.resttemplate.dto.User;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private RestTemplate restTemplate;
    private final String apiUrl;

    public UserServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public User getUser(String userId) {
        User user = null;
        try {
            user = restTemplate.getForObject(apiUrl + "/" + userId, User.class);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        //UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiUrl);
        //.queryParam("limit", limit);

        List<User> users = null;
        try {
            users = restTemplate.getForObject(apiUrl, List.class);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return users;
    }
}
