package com.petr.securityService.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticationRequestDto(
        @NotBlank(message = "Email is mandatory!")
        String username,

        @NotBlank(message = "Password is mandatory!")
        String password) implements Serializable {
}
