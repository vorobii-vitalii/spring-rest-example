package com.example.rest.repository;

import com.example.rest.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
