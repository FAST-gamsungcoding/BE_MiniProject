package com.gamsung.backend.global.jwt.dto;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
    public static JwtPair from(String accessToken, String refreshToken) {
        return new JwtPair(accessToken, refreshToken);
    }
}
