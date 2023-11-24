package com.gamsung.backend.global.jwt.service;

import com.gamsung.backend.global.jwt.JwtPair;
import com.gamsung.backend.global.jwt.JwtProvider;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import com.gamsung.backend.global.jwt.exception.JwtInvalidTokenException;
import com.gamsung.backend.global.jwt.repository.JwtRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtRedisRepository jwtRedisRepository;
    private final JwtProvider jwtProvider;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Value("${service.jwt.refresh-expiration}")
    private Long refreshExpiration;

    public JwtPair createTokenPair(JwtPayload jwtPayload) {
        String accessToken = jwtProvider.createToken(jwtPayload, accessExpiration);
        String refreshToken = jwtProvider.createToken(jwtPayload, refreshExpiration);

        jwtRedisRepository.save(JwtRedisEntity.builder()
                        .memberEmail(jwtPayload.getEmail())
                        .refreshToken(refreshToken)
                        .expiration(refreshExpiration)
                        .build());

        return JwtPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtPayload verifyToken(String jwtToken) {
        return jwtProvider.verifyToken(jwtToken);
    }

    public JwtPair refreshAccessToken(RefreshAccessTokenRequest request) {
        JwtPayload jwtPayload = verifyToken(request.refreshToken());

        jwtRedisRepository.findByKey(jwtPayload.getEmail())
                .ifPresentOrElse(refreshToken -> {
                    if (!request.refreshToken().equals(refreshToken)) {
                        throw new JwtInvalidTokenException("리프레시 토큰이 유효하지 않습니다.");
                    }
                }, () -> {
                    throw new JwtInvalidTokenException("리프레시 토큰이 존재하지 않습니다. 다시 로그인해 주세요.");
                });

        JwtPayload newJwtPayload = JwtPayload.from(jwtPayload.getEmail());

        String newAccessToken = jwtProvider.createToken(newJwtPayload, accessExpiration);

        return JwtPair.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.refreshToken())
                .build();
    }
}
