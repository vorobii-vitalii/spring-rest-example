package com.example.rest.utils;

import com.example.rest.dto.FavorDTO;
import com.example.rest.entity.Favor;
import org.springframework.stereotype.Service;

@Service
public class FavorMapper implements Mapper<Favor, FavorDTO> {

    @Override
    public FavorDTO to(Favor favor) {
        return FavorDTO.builder()
                .id(favor.getId())
                .description(favor.getDescription())
                .build();
    }

}
