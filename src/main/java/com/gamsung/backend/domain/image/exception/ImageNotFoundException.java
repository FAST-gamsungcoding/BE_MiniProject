package com.gamsung.backend.domain.image.exception;


import com.gamsung.backend.global.exception.BaseException;
import com.gamsung.backend.global.exception.ErrorCode;

public class ImageNotFoundException extends BaseException {

    public ImageNotFoundException() {
        super(ErrorCode.ACCOMODATION_NO_EXIST);
    }
}
