package com.github.dhslrl321.coupon.controller;

import com.github.dhslrl321.coupon.exception.AlreadyUsedException;
import com.github.dhslrl321.coupon.exception.CouponSoldOutException;
import com.github.dhslrl321.coupon.exception.DuplicateCouponAddException;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponApplicationExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AlreadyUsedException.class)
    public ResponseEntity<ErrorResponse> handle(AlreadyUsedException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(CouponSoldOutException.class)
    public ResponseEntity<ErrorResponse> handle(CouponSoldOutException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(DuplicateCouponAddException.class)
    public ResponseEntity<ErrorResponse> handle(DuplicateCouponAddException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handle(NoSuchElementException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private String reason;
    }
}
