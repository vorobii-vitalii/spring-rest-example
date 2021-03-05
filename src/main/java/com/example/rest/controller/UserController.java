package com.example.rest.controller;

import com.example.rest.entity.User;
import com.example.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable("id") Long id) {
        var user = userService.getUserById(id);

        return ResponseEntity.ok(user);
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


}
