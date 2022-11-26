package com.github.dhslrl321.coupon.model.data;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponAppliedResultData {
    Long couponId;
    String couponCode;
    LocalDateTime usedAt;
}
