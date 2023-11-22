package com.gamsung.backend.global.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
