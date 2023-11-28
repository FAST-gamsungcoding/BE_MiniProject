package com.gamsung.backend.domain.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorCode {
    BOOK_DATE_UNAVAILABLE(2002, "예약이 불가능한 날짜입니다."),
    ACCOMMODATION_NOT_FOUND(2003, "해당하는 숙소가 없습니다."),
    ORDER_SOLD_OUT(2005, "결제가 실패했습니다 예약이 불가능한 날짜가 있습니다.");

    private final int Status;
    private final String message;
}
