package com.gamsung.backend.domain.order.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class SoldOutOrder {
    long accommodationId;
    LocalDate startDate;
    LocalDate endDate;
}
