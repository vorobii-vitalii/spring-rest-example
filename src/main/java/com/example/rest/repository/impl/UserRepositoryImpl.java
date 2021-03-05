package com.example.rest.repository.impl;

import com.example.rest.entity.User;
import com.example.rest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = List.of(
            new User(1L),
            new User(2L)
    );

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst();
    }
}
