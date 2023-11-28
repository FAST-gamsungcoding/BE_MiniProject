package com.gamsung.backend.global.exception;

import com.gamsung.backend.global.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<String>> handleBaseException(BaseException e) {

        //실패 헤더에 담기는 코드는 부분은 badRequest가 아닌 경우 다르게 기본 http 상태코드를 확인 후 수정
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(e.getCode()), e.getMessage())
        );
    }

    @ExceptionHandler(UnAuthException.class)
    public ResponseEntity<ApiResponse> handleUnAuthException(UnAuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiResponse.builder()
                        .code(Integer.parseInt(e.getCode()))
                        .data(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApiResponse.builder()
                        .code(Integer.parseInt(e.getCode()))
                        .data(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse> handleBindValidationError(BindException e) {
        ErrorCode validationError = ErrorCode.VALIDATION_ERROR;
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(Integer.parseInt(validationError.getCode()))
                        .data(validationError.getMessage())
                        .build()
        );
    }
}
