package com.example.rest.service;

import com.example.rest.dto.FavorOrderDTO;
import com.example.rest.entity.FavorOrder;

public interface FavorOrderService {
    FavorOrder addNewFavorOrder(FavorOrderDTO favorOrderDTO);
    String getStatusOfFavorOrderById(Long id);
    void setStatusOfFavorOrderById(Long id, String newStatus);
    void editFavorOrderInfo(Long id, String newInfo);
}
