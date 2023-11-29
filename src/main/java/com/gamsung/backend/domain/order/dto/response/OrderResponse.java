package com.gamsung.backend.domain.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderResponse(
        LocalDateTime orderDate,
        long accommodationId,
        String accommodationName,
        String accommodationImg,
        int peopleNumber,
        LocalDate startDate,
        LocalDate endDate,
        String representativeName,
        long orderPrice
) {
   public static OrderResponse from(LocalDateTime orderDate, long accommodationId, String accommodationName,
                                    String accommodationImg, int peopleNumber, LocalDate startDate,
                                    LocalDate endDate, String representativeName, long orderPrice) {
      return new OrderResponse(orderDate, accommodationId, accommodationName,
              accommodationImg, peopleNumber, startDate,
              endDate, representativeName, orderPrice);
   }
}