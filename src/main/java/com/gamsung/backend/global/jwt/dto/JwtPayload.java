package com.gamsung.backend.global.jwt.dto;

import java.time.Instant;
import java.util.Date;

public record JwtPayload (
        String id,
        String email,
        Date issuedAt
) {
    public static JwtPayload from(Long id, String email) {
        return new JwtPayload(String.valueOf(id), email, Date.from(Instant.now()));
    }

    public static JwtPayload from(String id, String email, Date date) {
        return new JwtPayload(id, email, date);
    }
}
