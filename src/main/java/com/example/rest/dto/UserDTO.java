package com.example.rest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@RequiredArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    private Long id;
    private Map<String, String> properties = new HashMap<>();
}