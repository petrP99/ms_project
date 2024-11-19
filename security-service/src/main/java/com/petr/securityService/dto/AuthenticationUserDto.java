package com.petr.securityService.dto;


import com.petr.securityService.model.RoleNameEnum;

import java.io.Serializable;
import java.util.Set;

public record AuthenticationUserDto(String username,
                                    Set<RoleNameEnum> roleNames) implements Serializable {
}