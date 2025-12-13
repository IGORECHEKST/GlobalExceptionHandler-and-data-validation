package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationDto {

    @NotBlank(message = "Email є обов'язковим.")
    @Email(message = "Недійсний формат електронної пошти.")
    private String email;

    @NotBlank(message = "Пароль є обов'язковим.")
    @Size(min = 8, max = 50, message = "Пароль повинен бути від 8 до 50 символів.")
    private String password;

    @NotBlank(message = "Повне ім'я є обов'язковим.")
    @Size(max = 100, message = "Повне ім'я не може перевищувати 100 символів.")
    private String fullName;
}
