package com.petr.securityService.security.jwt;

import com.petr.securityService.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword()
        );
    }
}