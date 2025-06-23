package com.cooperawiki.Wiki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.infra.mappers.dto.input.UserInputDto;
import com.cooperawiki.Wiki.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    } 

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserInputDto dto) {
        return ResponseEntity.ok().body(userService.createUser(dto));
    }
    
}
