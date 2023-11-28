package com.gamsung.backend.domain.order.dto.response;

import java.util.List;

public record SoldOutErrorResponse (
    String message,
    List<SoldOutOrder> soldOutOrders
) {
    public static SoldOutErrorResponse create(String message, List<SoldOutOrder> soldOutOrders) {
        return new SoldOutErrorResponse(message, soldOutOrders);
    }
}
