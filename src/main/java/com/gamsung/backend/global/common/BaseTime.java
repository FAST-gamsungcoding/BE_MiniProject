package com.gamsung.backend.global.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class BaseTime {

    @NotNull(message ="시작 시간이  필요합니다.")
    private LocalDateTime startTime;

    @NotNull(message ="종료 시간이 필요합니다.")
    private LocalDateTime endTime;

}
