package com.example.rest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class FavorOrder {
    private Long id;
    private Favor favor;
    private User orderedBy;
    private String status;
    private String description;
}
