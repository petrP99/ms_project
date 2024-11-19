package com.petr.securityService.service.impl;

import com.petr.securityService.dto.AuthenticationRequestDto;
import com.petr.securityService.dto.AuthenticationUserDto;
import com.petr.securityService.mapper.AuthenticationUserMapper;
import com.petr.securityService.model.User;
import com.petr.securityService.repository.UserRepository;
import com.petr.securityService.service.AuthenticationService;
import com.petr.securityService.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationUserMapper authenticationUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.username());
        if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            AuthenticationUserDto authenticationUserDto = authenticationUserMapper.userToAuthenticationUserDto(user);
            log.info("IN findByEmailAndPassword - authenticationUserDto: {} found by username: {}", authenticationUserDto,
                    authenticationUserDto.username());
            return authenticationUserDto;
        } else {
            log.info("IN findByEmailAndPassword - Invalid username or password");
            return null;
        }
    }

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public String saveUSer(AuthenticationRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.username());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepository.save(user);
        return "User is created";
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
