package com.gamsung.backend.global.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ControllerResponse {
    private int code;
    private Object data;

    @Builder
    private ControllerResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }
}
