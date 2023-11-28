package com.gamsung.backend.domain.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class OrderResponse{
   LocalDateTime orderDate;
   long accommodationId;
   String accommodationName;
   String accommodationImg;
   int peopleNumber;
   LocalDate startDate;
   LocalDate endDate;
   String representativeName;
   long orderPrice;

}
