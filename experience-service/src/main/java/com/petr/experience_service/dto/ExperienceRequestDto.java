package com.petr.experience_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.time.YearMonth;

@Builder
public record ExperienceRequestDto(

        @NotNull(message = "Period from time is required")
        @PastOrPresent
        YearMonth periodFrom,

        @NotNull(message = "Period to time is required")
        @PastOrPresent
        YearMonth periodTo,

        @NotNull(message = "Present time is required")
        Boolean presentTime,

        @NotNull(message = "Industry id is required")
        Long industryId,

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
