package com.petr.industryservice.dto;

public record IndustryDto(Long id,
                          String name,
                          String direction,
                          String address,
                          String phone) {
}
