package com.github.dhslrl321.coupon.controller.command;

import com.github.dhslrl321.coupon.model.AssignCouponRequest;
import com.github.dhslrl321.coupon.model.AssignCouponSuccessResponse;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAssignedResultData;
import com.github.dhslrl321.coupon.service.command.AssignCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AssignCouponApiController {

    private final AssignCouponService service;

    @PostMapping("/users/{userId}/coupons")
    public ResponseEntity<AssignCouponSuccessResponse> assign(@PathVariable Long userId,
                                                              @RequestBody AssignCouponRequest body) {
        CouponAssignedResultData result = service.assign(AssignCouponRequestData.of(userId, body.getCode()));
        return ResponseEntity.ok(toResponse(result));
    }

    private AssignCouponSuccessResponse toResponse(CouponAssignedResultData result) {
        return new AssignCouponSuccessResponse(
                result.getCouponId(),
                result.getCouponCode(),
                result.getAssignedAt());
    }
}
