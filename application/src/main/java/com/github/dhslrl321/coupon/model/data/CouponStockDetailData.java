package com.github.dhslrl321.coupon.model.data;

import lombok.Value;

@Value(staticConstructor = "of")
public class CouponStockDetailData {
    String code;
    String name;
    Long amount;
    Long totalQuantity;
    Long leftQuantity;
}
