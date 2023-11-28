package com.gamsung.backend.global.exception;

import lombok.Getter;

@Getter
public class UnAuthException extends RuntimeException {
    private final ErrorCode errorCode;
    
    public UnAuthException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

}
