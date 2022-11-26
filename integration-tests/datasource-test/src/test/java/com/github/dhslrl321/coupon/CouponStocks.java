package com.github.dhslrl321.coupon;

import com.github.dhslrl321.coupon.core.Amount;
import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponName;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.core.CouponStockId;
import com.github.dhslrl321.coupon.core.Quantity;

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
