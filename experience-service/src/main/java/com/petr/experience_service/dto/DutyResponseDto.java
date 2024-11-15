package com.petr.experience_service.dto;

import java.io.Serializable;

public record DutyResponseDto(
        Long id,
        String dutyName
) implements Serializable {
}
