package com.github.dhslrl321.coupon.result;

import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponId;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponAssignedResult {
    CouponId couponId;
    CouponCode couponCode;
    LocalDateTime assignedAt;
}
