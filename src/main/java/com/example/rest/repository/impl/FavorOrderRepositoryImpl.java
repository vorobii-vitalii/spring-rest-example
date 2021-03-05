package com.example.rest.repository.impl;

import com.example.rest.entity.FavorOrder;
import com.example.rest.repository.FavorOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FavorOrderRepositoryImpl implements FavorOrderRepository {
    private final List<FavorOrder> favorOrders = new ArrayList<>();
    private static Long lastId = 1L;

    @Override
    public FavorOrder saveFavorOrder(FavorOrder favorOrderToSave) {

        synchronized (FavorOrderRepositoryImpl.class) {
            favorOrderToSave.setId(lastId++);
            favorOrders.add(favorOrderToSave);
        }

        return favorOrderToSave;
    }

    @Override
    public Optional<FavorOrder> findFavorOrderById(Long id) {
        return favorOrders
                    .stream()
                    .filter(favorOrder -> favorOrder.getId().equals(id))
                    .findFirst();
    }

}
