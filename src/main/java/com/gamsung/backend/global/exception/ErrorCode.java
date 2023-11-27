package com.gamsung.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //상태 코드,메시지를 담아서 ENUM 타입으로 쓰면 됩니다.

    // 멤버
    MEMBER_NOT_FOUND("1001", "회원 아이디가 존재하지 않습니다."),
    MEMBER_LOGIN_WRONG_PASSWORD("1002", "비밀번호가 올바르지 않습니다."),
    MEMBER_ALREADY_EXISTED("1007", "회원가입이 되어있는 이메일입니다."),

    // 숙박
    ACCOMODATION_NO_EXIST("3002","해당 상품의 정보가 없습니다."),
    ;


    private final String code;
    private final String message;
}
