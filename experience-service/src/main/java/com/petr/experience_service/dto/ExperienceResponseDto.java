package com.petr.experience_service.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.YearMonth;


@Builder
public record ExperienceResponseDto(
        Long id,
        Integer sequenceNumber,
        YearMonth periodFrom,
        YearMonth periodTo,
        Boolean presentTime,
        IndustryDto company,
        String position,
        String achievements,
        String link
) implements Serializable {
}
