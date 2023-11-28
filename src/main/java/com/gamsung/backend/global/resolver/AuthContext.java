package com.gamsung.backend.global.resolver;

public record AuthContext(
        String email
) {
    public static AuthContext from(String email) {
        return new AuthContext(email);
    }
}
