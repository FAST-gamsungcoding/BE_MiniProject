package com.gamsung.backend.global.jwt.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtPayload {
    private String email;
    private Date issuedAt;

    @Builder
    private JwtPayload(String email, Date issuedAt) {
        this.email = email;
        this.issuedAt = issuedAt;
    }

    public static JwtPayload from(String email) {
        return JwtPayload.builder()
                .email(email)
                .issuedAt(Date.from(Instant.now()))
                .build();
    }
}
