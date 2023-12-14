package com.gamsung.backend.domain.order.exception;

import com.gamsung.backend.global.exception.BaseException;
import com.gamsung.backend.global.exception.ErrorCode;
import lombok.Getter;


@Getter
public class BookDateUnavailableException extends BaseException {
    public BookDateUnavailableException() {
        super(ErrorCode.BOOK_DATE_UNAVAILABLE);
    }
}
