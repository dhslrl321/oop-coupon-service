package com.github.dhslrl321.coupon.model.data;

import lombok.Value;

@Value(staticConstructor = "of")
public class ApplyCouponRequestData {
    Long userId;
    Long couponId;
}
