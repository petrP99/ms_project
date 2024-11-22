package com.petr.securityService.service;

import com.petr.securityService.dto.AuthenticationDto;
import com.petr.securityService.model.User;
import com.petr.securityService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User findByEmailAndPassword(AuthenticationDto requestDto) {
        Optional<User> user = userRepository.findByUsername(requestDto.username());
        if (user.isPresent() && passwordEncoder.matches(requestDto.password(), user.get().getPassword())) {
            log.info("IN findByEmailAndPassword found by username: {}", user.get().getUsername());
            return user.get();
        } else {
            log.info("IN findByEmailAndPassword - Invalid username or password");
            return null;
        }
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String saveUSer(AuthenticationDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.username());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        userRepository.save(user);
        return "User is created";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
