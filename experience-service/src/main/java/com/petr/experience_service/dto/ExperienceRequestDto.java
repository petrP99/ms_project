package com.petr.experience_service.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.YearMonth;

@Builder
public record ExperienceRequestDto(
        @NotNull(message = "Sequence number is required")
        @Min(value = 1, message = "Sequence number must be at least 1")
        Integer sequenceNumber,

        @NotNull(message = "Period from is required")
        YearMonth periodFrom,

        @PastOrPresent
        @FutureOrPresent
        YearMonth periodTo,

        @NotNull(message = "Present time is required")
        Boolean presentTime,

        @NotBlank(message = "Company name is required")
        @Size(max = 128, message = "Company name must not exceed 128 characters")
        String company,

        @NotBlank(message = "Position is required")
        @Size(max = 255, message = "Position must not exceed 255 characters")
        String position,

        @Size(max = 255, message = "Achievements must not exceed 255 characters")
        String achievements,

        @Size(max = 255, message = "Link must not exceed 255 characters")
        String link
) implements Serializable {
}
