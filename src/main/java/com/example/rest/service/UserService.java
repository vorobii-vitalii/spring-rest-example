package com.example.rest.service;

import com.example.rest.entity.User;

public interface UserService {
    User getUserById(Long id);
    void addPropertyToUserById(Long id, String propertyKey, String propertyValue);
    void removePropertyOfUserById(Long id, String propertyKey);
}
