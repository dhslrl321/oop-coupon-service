package com.github.dhslrl321.coupon.result;

import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponId;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponAppliedResult {
    CouponId couponId;
    CouponCode couponCode;
    LocalDateTime usedAt;
}
