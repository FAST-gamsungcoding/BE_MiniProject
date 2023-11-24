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
//모든 Entity는 BaseTime을 상속받으면 됩니다.
//@EntityListeners(AuditingEntityListener.class) 상속받는 엔티티에 해당 어노테이션 고정으로 박아두시면 됩니다.
public class BaseTime {

    @NotNull(message ="시작 시간이  필요합니다.")
    private LocalDateTime startTime;

    @NotNull(message ="종료 시간이 필요합니다.")
    private LocalDateTime endTime;

}
