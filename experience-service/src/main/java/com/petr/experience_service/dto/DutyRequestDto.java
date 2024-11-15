package com.petr.experience_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record DutyRequestDto(
        @NotNull(message = "duty name is mandatory!")
        Long id,

        @NotBlank(message = "duty name is mandatory!")
        @Size(max = 255, message = "duty name must not exceed 255 characters")
        String dutyName) implements Serializable {
}
