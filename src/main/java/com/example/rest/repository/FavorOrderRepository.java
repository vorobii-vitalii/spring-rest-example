package com.example.rest.repository;

import com.example.rest.entity.FavorOrder;

import java.util.Optional;

public interface FavorOrderRepository {
    FavorOrder saveFavorOrder(FavorOrder favorOrderToSave);
    Optional<FavorOrder> findFavorOrderById(Long id);
}
