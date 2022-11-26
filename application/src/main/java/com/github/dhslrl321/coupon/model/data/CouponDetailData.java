package com.github.dhslrl321.coupon.model.data;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponDetailData {
    Long couponId;
    String code;
    String state;
    LocalDateTime assignedAt;
    LocalDateTime usedAt;
}
