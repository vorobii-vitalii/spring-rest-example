package com.example.rest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class Favor {
    private final Long id;
    private final String description;
}
