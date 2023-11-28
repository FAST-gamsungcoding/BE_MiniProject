package com.gamsung.backend.domain.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDateUnavailableException extends RuntimeException{
    private OrderErrorCode errorCode;
}
