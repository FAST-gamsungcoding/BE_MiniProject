package com.gamsung.backend.domain.image.exception;


import com.gamsung.backend.global.exception.BaseException;
import com.gamsung.backend.global.exception.ErrorCode;

public class ImageNotFoundException extends BaseException {
    private final ErrorCode errorCode;

    public ImageNotFoundException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
