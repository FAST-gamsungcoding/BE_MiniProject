package com.gamsung.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private final Integer code;        // 사용자 정의 코드
    private final T data;           // API에서 반환되는 데이터

}