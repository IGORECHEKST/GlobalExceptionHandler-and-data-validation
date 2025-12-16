package com.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody @Valid UserCreationDto userDto) {
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }
}
