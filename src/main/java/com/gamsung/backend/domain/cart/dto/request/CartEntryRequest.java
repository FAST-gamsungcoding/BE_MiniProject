package com.gamsung.backend.domain.cart.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartEntryRequest {
    private Long accommodationId;
    private String name;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long people;
    private Long cartPrice;
    private String accommodationImg;
}
