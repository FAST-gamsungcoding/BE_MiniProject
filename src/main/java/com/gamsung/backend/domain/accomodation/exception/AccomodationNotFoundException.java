package com.gamsung.backend.domain.accomodation.exception;

import com.gamsung.backend.global.common.BaseException;
import com.gamsung.backend.global.common.ErrorCode;

public class AccomodationNotFoundException extends BaseException {

    public AccomodationNotFoundException(){
        super(ErrorCode.ACCOMODATION_NO_EXIST);
    }
}
