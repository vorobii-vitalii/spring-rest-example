package com.example.rest.controller;

import com.example.rest.StatusType;
import com.example.rest.dto.FavorOrderDTO;
import com.example.rest.entity.Favor;
import com.example.rest.entity.FavorOrder;
import com.example.rest.service.FavorOrderService;
import com.example.rest.service.FavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favors")
@RequiredArgsConstructor
public class FavorController {
    private final FavorService favorService;
    private final FavorOrderService favorOrderService;

    @GetMapping
    public ResponseEntity<List<Favor>> getAllFavors() {
        var favors = favorService.getAllFavors();

        return ResponseEntity.ok(favors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favor> getFavorDetailsById(@PathVariable(name = "id") Long id) {
        var favor = favorService.getFavorById(id);

        return ResponseEntity.ok(favor);
    }

    @PostMapping("/orders")
    public ResponseEntity<FavorOrder> createFavorOrder(
            @RequestBody FavorOrderDTO favorOrderDTO
    ) {
        var savedFavorOrder =
                favorOrderService.addNewFavorOrder(favorOrderDTO);

        return ResponseEntity.ok(savedFavorOrder);
    }

    @GetMapping("/orders/{favorOrderId}/status")
    public ResponseEntity<String> getFavorOrderStatus(
            @PathVariable("favorOrderId") Long id
    ) {
        var status = favorOrderService.getStatusOfFavorOrderById(id);

        return ResponseEntity.ok(status);
    }

    @PutMapping("/orders/{favorOrderId}")
    public ResponseEntity<String> cancelFavorOrderById(
            @PathVariable("favorOrderId") Long id
    ) {
        favorOrderService.setStatusOfFavorOrderById(id, StatusType.CANCELLED);

        var message =
                String.format(
                        "Successfully cancelled favor-order by id %d",
                        id
                );

        return ResponseEntity.ok(message);
    }

    @PutMapping("/orders/{favorOrderId}/info")
    public ResponseEntity<String> setFavorOrderInfo(
            @PathVariable("favorOrderId") Long id,
            @RequestParam("value") String newInfo
    ) {
        favorOrderService.editFavorOrderInfo(id, newInfo);

        var message =
                String.format(
                        "Successfully changed the info of favor-order by id %d", id
                );

        return ResponseEntity.ok(message);
    }


}
