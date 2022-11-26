package com.github.dhslrl321.coupon.model.data;

import java.util.List;
import lombok.Value;

@Value(staticConstructor = "of")
public class CouponStockDetailsData {
    List<CouponStockDetailData> stocks;
}
