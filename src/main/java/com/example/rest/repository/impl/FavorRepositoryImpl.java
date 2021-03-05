package com.example.rest.repository.impl;

import com.example.rest.entity.Favor;
import com.example.rest.repository.FavorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FavorRepositoryImpl implements FavorRepository {
    private final List<Favor> favors = List.of(
        new Favor(1L, "Fetch a driver license"),
        new Favor(2L, "Fetch a banking details")
    );

    @Override
    public List<Favor> getAllFavors() {
        return favors;
    }

    @Override
    public Optional<Favor> findFavorById(Long id) {
        return favors.stream()
                    .filter(favor -> favor.getId().equals(id))
                    .findFirst();
    }

}
