package com.example.demo.service;

import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public UserDto register(RegisterUserRequestDto requestDto) {

        Optional<User> existingUser = userRepository.findByEmail(requestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + requestDto.getEmail() + " already exists.");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());

        User savedUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFirstName(savedUser.getFirstName());

        return userDto;
    }
}
