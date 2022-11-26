package com.github.dhslrl321.coupon.controller.query;

import com.github.dhslrl321.coupon.model.data.CouponDetailsData;
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
public class QueryUserApiController {

    private final QueryViewGenerator viewGenerator;

    @GetMapping("/users/{userId}/coupons")
    public ResponseEntity<CouponDetailsData> getCouponsBy(@PathVariable Long userId) {
        return ResponseEntity.ok(viewGenerator.userCoupons(userId));
    }
}
