package com.petr.securityService.service;


import com.petr.securityService.dto.AuthenticationRequestDto;
import com.petr.securityService.dto.AuthenticationUserDto;

public interface AuthenticationService {

    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);

    String getUsername();

    String saveUSer(AuthenticationRequestDto requestDto);

    String generateToken(String username);

    void validateToken(String token);
}
