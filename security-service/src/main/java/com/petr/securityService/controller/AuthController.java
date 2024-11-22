package com.petr.securityService.controller;

import com.petr.securityService.dto.AuthenticationDto;
import com.petr.securityService.model.User;
import com.petr.securityService.service.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public String createUser(@Valid @RequestBody AuthenticationDto requestDto) {
        return authService.saveUSer(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthenticationDto requestDto) {
        User user = authService.findByEmailAndPassword(requestDto);
        if (isNull(user)) {
            throw new BadRequestException("Wrong username or password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("token", authService.generateToken(requestDto.username()));
        return ResponseEntity.ok(response);
    }


    @PostMapping("/token")
    public String getToken(@Valid @RequestBody AuthenticationDto requestDto, AuthenticationManager authenticationManager) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password()));
        if (authentication.isAuthenticated()) {
            return authService.generateToken(requestDto.username());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validate(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
