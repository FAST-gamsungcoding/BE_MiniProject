package com.gamsung.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //상태 코드,메시지를 담아서 ENUM 타입으로 쓰면 됩니다.
    ACCOMODATION_NO_EXIST("3002","해당 상품의 정보가 없습니다."),
    ;


    private final String code;
    private final String message;
}
