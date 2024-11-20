package com.petr.securityService.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Value("${spring.security.jwt.expired}")
    private long validityInMilliseconds;

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, getKey()).compact();
    }

    private SecretKeySpec getKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secret.getBytes()), SignatureAlgorithm.HS256.getJcaName());
    }
}
