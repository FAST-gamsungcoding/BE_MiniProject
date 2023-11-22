package com.gamsung.backend.global.jwt.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

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
}
