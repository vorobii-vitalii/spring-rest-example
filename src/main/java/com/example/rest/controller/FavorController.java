package com.example.rest.controller;

import com.example.rest.StatusType;
import com.example.rest.dto.FavorDTO;
import com.example.rest.dto.FavorOrderDTO;
import com.example.rest.entity.Favor;
import com.example.rest.entity.FavorOrder;
import com.example.rest.service.FavorOrderService;
import com.example.rest.service.FavorService;
import com.example.rest.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/favors")
@RequiredArgsConstructor
public class FavorController {
    private final FavorService favorService;
    private final FavorOrderService favorOrderService;
    private final Mapper<Favor, FavorDTO> favorDTOMapper;
    private final Mapper<FavorOrder, FavorOrderDTO> favorOrderDTOMapper;

    @GetMapping
    public CollectionModel<FavorDTO> getAllFavors() {
        var favors = favorService.getAllFavors();

        var favorDTOs = favors.stream()
                .map(favorDTOMapper::to)
                .map(FavorController::attachFavorLink)
                .collect(Collectors.toList());

        var getAllFavorLink = linkTo(
                methodOn(FavorController.class)
                    .getAllFavors())
                .withSelfRel();

        return CollectionModel.of(favorDTOs, getAllFavorLink);
    }

    @GetMapping("/{id}")
    public CollectionModel<FavorDTO> getFavorDetailsById(@PathVariable(name = "id") Long id) {

        var favorDTO =
                Optional.ofNullable(favorService.getFavorById(id))
                    .map(favorDTOMapper::to)
                    .map(FavorController::attachFavorLink)
                .orElse(null);

        var getAllFavorLink = linkTo(methodOn(FavorController.class)
                .getAllFavors()).withSelfRel();

        return CollectionModel.of(Collections.singletonList(favorDTO), getAllFavorLink);
    }

    @PostMapping("/orders")
    public ResponseEntity<FavorOrderDTO> createFavorOrder(
            @RequestBody FavorOrderDTO favorOrderDTO
    ) {
        var savedFavorOrder =
                favorOrderService.addNewFavorOrder(favorOrderDTO);

        return ResponseEntity.ok(favorOrderDTOMapper.to(savedFavorOrder));
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

    private static FavorDTO attachFavorLink(FavorDTO favorDTO) {
        var favorSelfLink = linkTo(
                methodOn(FavorController.class)
                        .getFavorDetailsById(favorDTO.getId()))
                .withSelfRel();
        favorDTO.add(favorSelfLink);
        return favorDTO;
    }


}
