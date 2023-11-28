package com.gamsung.backend.domain.image.exception;


import com.gamsung.backend.global.exception.BaseException;
import com.gamsung.backend.global.exception.ErrorCode;

public class ImageNotFoundException extends BaseException {

    public ImageNotFoundException() {
        // 추후 오류 메시지 추가 예정
        super(ErrorCode.ACCOMMODATION_NO_EXIST);
    }
}
