package com.petr.experience_service.dto;

import lombok.Builder;

import java.io.Serializable;


@Builder
public record ExperienceResponseDto(
        Long id,
        Integer sequenceNumber,
        String periodFrom,
        String periodTo,
        Boolean presentTime,
        String company,
        String position,
        String achievements,
        String link
) implements Serializable {
}
