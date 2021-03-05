package com.example.rest.service.impl;

import com.example.rest.entity.User;
import com.example.rest.repository.UserRepository;
import com.example.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository
                    .findById(id)
                    .orElseThrow(RuntimeException::new);
    }

    @Override
    public void addPropertyToUserById(Long id, String propertyKey, String propertyValue) {
        var user = getUserById(id);

        user.getProperties().put(propertyKey, propertyValue);
    }

    @Override
    public void removePropertyOfUserById(Long id, String propertyKey) {
        var user = getUserById(id);

        user.getProperties().remove(propertyKey);
    }

}
