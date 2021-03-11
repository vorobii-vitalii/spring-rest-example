package com.example.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class FavorDTO extends RepresentationModel<FavorDTO> {
    private Long id;
    private String description;
}
