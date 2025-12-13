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

    @NotBlank(message = "Email є обов'язковим і не може бути порожнім.")
    @Email(message = "Введено недійсний формат електронної пошти.")
    private String email;

    @NotBlank(message = "Пароль є обов'язковим.")
    @Size(min = 8, max = 50, message = "Пароль повинен містити від 8 до 50 символів.")
    private String password;

    @NotBlank(message = "Ім'я є обов'язковим.")
    @Size(max = 50, message = "Ім'я не може перевищувати 50 символів.")
    private String firstName;
}
