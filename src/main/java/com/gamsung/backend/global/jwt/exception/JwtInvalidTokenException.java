package com.gamsung.backend.global.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtInvalidTokenException extends AuthenticationException {
    public JwtInvalidTokenException(String msg) {
        super(msg);
    }
}
