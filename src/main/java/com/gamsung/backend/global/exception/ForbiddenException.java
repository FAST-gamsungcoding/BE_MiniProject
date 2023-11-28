package com.gamsung.backend.global.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class ForbiddenException extends AuthenticationException {
    private final ErrorCode errorCode;

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

}
