package com.github.dhslrl321.coupon.controller.query;

import com.github.dhslrl321.coupon.model.data.CouponDetailWithUserData;
import com.github.dhslrl321.coupon.model.data.CouponStockDetailsData;
import com.github.dhslrl321.coupon.service.query.QueryViewGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class QueryCouponApiController {
    private final QueryViewGenerator viewGenerator;

    @GetMapping("/coupons")
    public ResponseEntity<CouponStockDetailsData> getAll() {
        return ResponseEntity.ok(viewGenerator.couponStocks());
    }

    @GetMapping("/coupons/{couponId}")
    public ResponseEntity<CouponDetailWithUserData> getBy(@PathVariable Long couponId) {
        return ResponseEntity.ok(viewGenerator.couponBy(couponId));
    }
}
