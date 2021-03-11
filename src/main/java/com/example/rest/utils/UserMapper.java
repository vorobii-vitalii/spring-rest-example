package com.example.rest.utils;

import com.example.rest.dto.UserDTO;
import com.example.rest.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public UserDTO to(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .properties(user.getProperties())
                .build();
    }
}
