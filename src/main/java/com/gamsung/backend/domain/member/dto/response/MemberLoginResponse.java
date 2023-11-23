package com.gamsung.backend.domain.member.dto.response;


import com.gamsung.backend.global.jwt.JwtPair;

public record MemberLoginResponse(
        String accessToken,
        String refreshToken
) {
    public static MemberLoginResponse from(JwtPair jwtPair) {
        return new MemberLoginResponse(jwtPair.getAccessToken(), jwtPair.getRefreshToken());
    }
}
