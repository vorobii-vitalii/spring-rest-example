package com.example.rest.repository;

import com.example.rest.entity.Favor;

import java.util.List;
import java.util.Optional;

public interface FavorRepository {
    List<Favor> getAllFavors();
    Optional<Favor> findFavorById(Long id);
}
