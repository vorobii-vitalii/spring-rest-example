package com.example.rest.utils;

import com.example.rest.dto.FavorOrderDTO;
import com.example.rest.entity.FavorOrder;
import org.springframework.stereotype.Service;

@Service
public class FavorOrderMapper implements Mapper<FavorOrder, FavorOrderDTO> {
    @Override
    public FavorOrderDTO to(FavorOrder favorOrder) {
        return FavorOrderDTO.builder()
                .favorId(favorOrder.getFavor().getId())
                .description(favorOrder.getDescription())
                .build();
    }
}
