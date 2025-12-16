package com.example.demo.controller;

import com.example.demo.dto.RegisterUserRequestDto;
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

            @RequestBody @Valid RegisterUserRequestDto userDto) {
        return new ResponseEntity<>("User registered successfully and passed validation!", HttpStatus.CREATED);
    }
}
