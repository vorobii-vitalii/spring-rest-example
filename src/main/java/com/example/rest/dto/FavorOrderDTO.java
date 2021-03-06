package com.example.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class FavorOrderDTO extends RepresentationModel<FavorOrderDTO> {
    private Long userId;
    private Long favorId;
    private String description;
}
