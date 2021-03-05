package com.example.rest.service.impl;

import com.example.rest.entity.Favor;
import com.example.rest.repository.FavorRepository;
import com.example.rest.service.FavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavorServiceImpl implements FavorService {
    private final FavorRepository favorRepository;

    @Override
    public List<Favor> getAllFavors() {
        return favorRepository.getAllFavors();
    }

    @Override
    public Favor getFavorById(Long id) {
        return favorRepository
                    .findFavorById(id)
                    .orElseThrow(RuntimeException::new);
    }

}
