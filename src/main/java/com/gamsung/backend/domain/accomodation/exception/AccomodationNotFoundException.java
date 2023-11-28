package com.gamsung.backend.domain.accomodation.exception;


import com.gamsung.backend.global.exception.BaseException;
import com.gamsung.backend.global.exception.ErrorCode;

public class AccomodationNotFoundException extends BaseException {

    public AccomodationNotFoundException(){
        super(ErrorCode.ACCOMODATION_NO_EXIST);
    }
}
