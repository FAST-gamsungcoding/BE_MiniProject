package com.gamsung.backend.domain.order.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderAccommodationRequest {
    long accommodationId;
    int peopleNumber;
    LocalDate startDate;
    LocalDate endDate;
    String representativeName;
    String representativeEmail;
    int orderPrice;
    long cartId;
}
