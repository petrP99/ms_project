package com.petr.securityService.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthenticationDto(
        String username,
        String password) implements Serializable {
}
