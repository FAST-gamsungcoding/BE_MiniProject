package com.gamsung.backend.domain.order.dto.response;

import java.time.LocalDate;

public record SoldOutOrder(
    long accommodationId,
    LocalDate startDate,
    LocalDate endDate
){
    public static SoldOutOrder from(long accommodationId,
                                    LocalDate startDate, LocalDate endDate){
        return new SoldOutOrder(accommodationId,startDate,endDate);
    }
}
