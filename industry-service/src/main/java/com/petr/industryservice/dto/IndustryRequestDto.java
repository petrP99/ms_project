package com.petr.industryservice.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record IndustryRequestDto(
        @NotNull(message = "Company обязательно для заполенения")
        @Length(min = 3, max = 40)
        String name) {
}
