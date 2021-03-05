package com.example.rest.service;

import com.example.rest.entity.Favor;

import java.util.List;

public interface FavorService {
    List<Favor> getAllFavors();
    Favor getFavorById(Long id);
}
