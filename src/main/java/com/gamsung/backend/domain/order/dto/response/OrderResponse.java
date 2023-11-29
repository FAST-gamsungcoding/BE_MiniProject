package com.gamsung.backend.domain.order.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderResponse(
        LocalDate orderDatetime,
        long accommodationId,
        String accommodationName,
        String accommodationImg,
        int peopleNumber,
        LocalDate startDate,
        LocalDate endDate,
        String representativeName,
        long orderPrice
) {
   public static OrderResponse from(LocalDate orderDate, long accommodationId, String accommodationName,
                                    String accommodationImg, int peopleNumber, LocalDate startDate,
                                    LocalDate endDate, String representativeName, long orderPrice) {
      return new OrderResponse(orderDate, accommodationId, accommodationName,
              accommodationImg, peopleNumber, startDate,
              endDate, representativeName, orderPrice);
   }
}