package com.example.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavorOrderDTO {
    private final Long userId;
    private final Long favorId;
    private final String description;
}
