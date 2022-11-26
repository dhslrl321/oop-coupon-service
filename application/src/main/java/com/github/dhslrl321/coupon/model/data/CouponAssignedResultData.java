package com.github.dhslrl321.coupon.model.data;

import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponAssignedResultData {
    Long couponId;
    String couponCode;
    LocalDateTime assignedAt;
}
