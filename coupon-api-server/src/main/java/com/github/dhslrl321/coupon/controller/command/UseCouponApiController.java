package com.github.dhslrl321.coupon.controller.command;

import com.github.dhslrl321.coupon.model.UseCouponSuccessResponse;
import com.github.dhslrl321.coupon.model.data.ApplyCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAppliedResultData;
import com.github.dhslrl321.coupon.service.command.ApplyCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UseCouponApiController {
    private final ApplyCouponService service;

    @PatchMapping("/users/{userId}/coupons/{couponId}")
    public ResponseEntity<UseCouponSuccessResponse> assign(@PathVariable Long userId, @PathVariable Long couponId) {
        CouponAppliedResultData result = service.apply(ApplyCouponRequestData.of(userId, couponId));
        return ResponseEntity.ok(toResponse(result));
    }

    private UseCouponSuccessResponse toResponse(CouponAppliedResultData result) {
        return new UseCouponSuccessResponse(result.getCouponId(), result.getCouponCode(), result.getUsedAt());
    }
}
