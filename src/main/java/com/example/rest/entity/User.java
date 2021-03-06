package com.example.rest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
@RequiredArgsConstructor
public class User {
    private Long id;
    private Map<String, String> properties = new HashMap<>();
    private List<FavorOrder> favorOrders = new ArrayList<>();
}
