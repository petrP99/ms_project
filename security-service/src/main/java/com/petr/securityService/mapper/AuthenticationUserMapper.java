package com.petr.securityService.mapper;

import com.petr.securityService.dto.AuthenticationUserDto;
import com.petr.securityService.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthenticationUserMapper {

    AuthenticationUserDto userToAuthenticationUserDto(User user);

}
