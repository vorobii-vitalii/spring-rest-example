package com.example.rest.controller;

import com.example.rest.dto.FavorDTO;
import com.example.rest.dto.UserDTO;
import com.example.rest.entity.User;
import com.example.rest.service.UserService;
import com.example.rest.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapper<User, UserDTO> userDTOMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") Long id) {
        var userDTO =
                Optional
                    .ofNullable(userService.getUserById(id))
                    .map(userDTOMapper::to)
                    .map(UserController::attachFavorLink)
                .orElseThrow();

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/{id}/attributes/{attributeName}")
    public ResponseEntity<String> changeUsersAttributeValue(
            @PathVariable("id") Long id,
            @PathVariable("attributeName") String attributeName,
            @RequestParam("value") String attributeValue
    ) {

        userService.addPropertyToUserById(
                id,
                attributeName,
                attributeValue
        );

        var message = String.format(
                "Attribute %s added or changed",
                attributeName
        );

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}/attributes/{attributeName}")
    public ResponseEntity<String> deleteUsersAttribute(
            @PathVariable("id") Long id,
            @PathVariable("attributeName") String attributeName
    ) {

        userService.removePropertyOfUserById(id, attributeName);

        var message = String.format(
                "Attribute %s was deleted",
                attributeName
        );

        return ResponseEntity.ok(message);
    }

    private static UserDTO attachFavorLink(UserDTO userDTO) {
        var userSelfLink = linkTo(
                methodOn(UserController.class)
                        .getUserDetails(userDTO.getId()))
                .withSelfRel();
        userDTO.add(userSelfLink);
        return userDTO;
    }

}
