package com.github.dhslrl321.coupon.value;

import lombok.Value;

@Value(staticConstructor = "of")
public class CouponStockId {
    Long value;
}
