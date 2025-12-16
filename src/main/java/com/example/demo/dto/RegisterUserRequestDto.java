package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {

    @NotBlank(message = "Email is mandatory and cannot be empty.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters.")
    private String password;

    @NotBlank(message = "First name is mandatory.")
    @Size(max = 50, message = "First name cannot exceed 50 characters.")
    private String firstName;
}
