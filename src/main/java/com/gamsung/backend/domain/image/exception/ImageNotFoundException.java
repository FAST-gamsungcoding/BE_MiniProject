package com.gamsung.backend.domain.image.exception;

import com.gamsung.backend.global.common.BaseException;
import com.gamsung.backend.global.common.ErrorCode;

public class ImageNotFoundException extends BaseException {

    public ImageNotFoundException() {
        super(ErrorCode.ACCOMODATION_NO_EXIST);
    }
}
