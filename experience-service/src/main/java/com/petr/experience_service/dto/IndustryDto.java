package com.petr.experience_service.dto;

import lombok.Builder;

@Builder
public record IndustryDto(Long id,
                          String name,
                          String direction,
                          String address,
                          String phone) {
}
