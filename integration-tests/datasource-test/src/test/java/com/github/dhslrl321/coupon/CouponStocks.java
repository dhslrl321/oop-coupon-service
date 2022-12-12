package com.github.dhslrl321.coupon;

import com.github.dhslrl321.coupon.value.Amount;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponName;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.value.CouponStockId;
import com.github.dhslrl321.coupon.value.Quantity;

public class CouponStocks {
    public static CouponStock couponStock(CouponStockId id) {
        return CouponStock.from(
                id,
                CouponName.of("할인 쿠폰"),
                CouponCode.of("C0001"),
                Amount.of(50000L),
                Quantity.of(100L), Quantity.of(100L));
    }

    public static CouponStock couponStock(CouponStockId id, CouponCode code) {
        return CouponStock.from(
                id,
                CouponName.of("할인 쿠폰"),
                code,
                Amount.of(50000L),
                Quantity.of(100L), Quantity.of(100L));
    }

    public static CouponStock couponStock(CouponStockId id, CouponCode code, Quantity totalQuantity) {
        return CouponStock.from(
                id,
                CouponName.of("할인 쿠폰"),
                code,
                Amount.of(50000L),
                totalQuantity, totalQuantity);
    }
}
