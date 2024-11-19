package com.petr.securityService.controller;

import com.petr.securityService.dto.AuthenticationRequestDto;
import com.petr.securityService.dto.AuthenticationUserDto;
import com.petr.securityService.security.jwt.JwtTokenProvider;
import com.petr.securityService.service.impl.AuthenticationServiceImpl;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationServiceImpl authService;

    @PostMapping("/register")
    public String createUser(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        return authService.saveUSer(requestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authService.findByEmailAndPassword(requestDto);
        if (user == null) {
            throw new BadRequestException("Wrong username or password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("username", user.username());
        response.put("token", jwtTokenProvider.createToken(user));
        return ResponseEntity.ok(response);
    }


//    @PostMapping("/token")
//    public String getToken(@Valid @RequestBody AuthenticationRequestDto requestDto, AuthenticationManager authenticationManager) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password()));
//        if (authentication.isAuthenticated()) {
//            return authService.generateToken(requestDto.username());
//        } else {
//            throw new RuntimeException("invalid access");
//        }
//    }

    @GetMapping("/validate")
    public String validate(@RequestParam("token") String token) {
        jwtTokenProvider.validateToken(token);
//                authService.validateToken(token);
        return "Token is valid";
    }
}
