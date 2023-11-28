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

    // JWT
    JWT_EXPIRED_REFRESH_TOKEN("1009", "리프레시 토큰이 만료되었습니다."),
    JWT_INVALID_REFRESH_TOKEN("1010", "리프레시 토큰이 유효하지 않습니다."),

    // 숙박
    ACCOMODATION_NO_EXIST("3002","해당 상품의 정보가 없습니다."),

    // 공통
    JWT_INVALID_ACCESS_TOKEN("5000", "액세스 토큰이 유효하지 않습니다."),
    JWT_EXPIRED_ACCESS_TOKEN("5001", "액세스 토큰이 만료되었습니다."),
    SERVICE_ERROR("5002", "서비스 오류입니다."),
    VALIDATION_ERROR("5003", "데이터 형식이 올바르지 않습니다."),

    ;


    private final String code;
    private final String message;
}