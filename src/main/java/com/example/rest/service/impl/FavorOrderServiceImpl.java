package com.example.rest.service.impl;

import com.example.rest.StatusType;
import com.example.rest.dto.FavorOrderDTO;
import com.example.rest.entity.FavorOrder;
import com.example.rest.repository.FavorOrderRepository;
import com.example.rest.service.FavorOrderService;
import com.example.rest.service.FavorService;
import com.example.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavorOrderServiceImpl implements FavorOrderService {
    private final FavorOrderRepository favorOrderRepository;
    private final FavorService favorService;
    private final UserService userService;

    @Override
    public FavorOrder addNewFavorOrder(FavorOrderDTO favorOrderDTO) {
        var favorOrder = fromDto(favorOrderDTO);

        return favorOrderRepository.saveFavorOrder(favorOrder);
    }

    @Override
    public String getStatusOfFavorOrderById(Long id) {
        var favorOrder = favorOrderRepository
                                         .findFavorOrderById(id)
                                         .orElseThrow();
        return favorOrder.getStatus();
    }

    @Override
    public void setStatusOfFavorOrderById(Long id, String newStatus) {
        var favorOrder = favorOrderRepository
                .findFavorOrderById(id)
                .orElseThrow();

        favorOrder.setStatus(newStatus);
    }

    @Override
    public void editFavorOrderInfo(Long id, String newInfo) {
        var favorOrder = favorOrderRepository
                .findFavorOrderById(id)
                .orElseThrow();

        favorOrder.setDescription(newInfo);
    }

    private FavorOrder fromDto(FavorOrderDTO favorOrderDTO) {

        var favor = favorService.getFavorById(
                favorOrderDTO.getFavorId());

        var orderedBy = userService.getUserById(
                favorOrderDTO.getUserId());

        return FavorOrder
                .builder()
                .favor(favor)
                .orderedBy(orderedBy)
                .description(favorOrderDTO.getDescription())
                .status(StatusType.CREATED)
                .build();
    }

}
