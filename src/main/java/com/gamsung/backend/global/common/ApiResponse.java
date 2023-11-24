package com.gamsung.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private final int code;        // HTTP 상태 코드 또는 사용자 정의 코드
    private final T data;           // API에서 반환되는 데이터

    // 성공 응답 생성 메서드
    public static <T> ApiResponse<T> success(int code, T data) {
        return new ApiResponse<>(code, data);
    }

    // 실패 응답 생성 메서드
    public static ApiResponse<String> fail(int code, String message) {
        return new ApiResponse<>(code, message);
    }
}