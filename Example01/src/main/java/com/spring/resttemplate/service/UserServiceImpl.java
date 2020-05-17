package com.spring.resttemplate.service;

import com.spring.resttemplate.dto.User;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public User getSingleUser(String userId) {
        User user = null;
        try {
            user = restTemplate.getForObject(apiUrl + "/" + userId, User.class);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return user;
    }

    @Override
    public User getSingleUserWithUriVariable(String userId) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", userId);

        User user = null;
        try {
            user = restTemplate.getForObject(apiUrl + "/{id}", User.class, uriVariables);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = restTemplate.getForObject(apiUrl, List.class);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return users;
    }

    @Override
    public List<User> getUserQueryParam(String id, String userName, String email) {
        String providerScheme = "https";
        String providerHost = "jsonplaceholder.typicode.com";
        String providerPath = "users";
        String providerPort = null;

        Map<String, String> queryParams = new HashMap<>();
        if (Objects.nonNull(id)) {
            queryParams.put("id", id);
        }
        if (Objects.nonNull(userName)) {
            queryParams.put("username", userName);
        }
        if (Objects.nonNull(email)) {
            queryParams.put("email", email);
        }

        HttpHeaders headers = buildHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        URI uri = buildUri(providerScheme, providerHost, providerPort, providerPath, queryParams);

        List<User> users = null;
        try {
            ResponseEntity<?> response =
                    restTemplate.exchange(uri, HttpMethod.GET, httpEntity, List.class);
            users = (List<User>) response.getBody();
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        User createdUser = null;
        try {
            createdUser = restTemplate.postForObject(apiUrl, user, User.class);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return createdUser;
    }

    @Override
    public User createUser2(User user) {
        HttpHeaders headers = buildHeaders();
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);

        ResponseEntity<User> response;
        User createdUser = null;
        try {
            response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, User.class);
            createdUser = response.getBody();
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return createdUser;
    }

    @Override
    public void updateUser(String userId, User user) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", userId);

        try {
            restTemplate.put(apiUrl + "/" + userId, user, uriVariables);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
    }

    @Override
    public User updateUser2(String userId, User user) {
        String providerScheme = "https";
        String providerHost = "jsonplaceholder.typicode.com";
        String providerPath = "users/" + userId;
        String providerPort = null;

        HttpHeaders headers = buildHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        URI uri = buildUri(providerScheme, providerHost, providerPort, providerPath, null);

        ResponseEntity<User> response;
        User updatedUser = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, User.class);
            updatedUser = response.getBody();
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
        return updatedUser;
    }

    @Override
    public void deleteUser(String userId) {
        try {
            restTemplate.delete(apiUrl + "/" + userId);
        } catch (Exception ex) {
            log.error("Exception occurred", ex);
        }
    }

    private URI buildUri(String providerScheme, String providerHost, String providerPort, String providerPath,
                         Map<String, String> queryParams) {
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.newInstance();
        urlBuilder.scheme(providerScheme);
        urlBuilder.host(providerHost);
        urlBuilder.path(providerPath);

        if (providerPort != null) {
            urlBuilder.port(providerPort);
        }

        if (queryParams != null) {
            queryParams.forEach(urlBuilder::queryParam);
        }

        return urlBuilder.build(false).encode().toUri();
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();

        // Need to add in order to tell HttpClient to end the TCP session
        headers.add("Connection", "close");

        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
