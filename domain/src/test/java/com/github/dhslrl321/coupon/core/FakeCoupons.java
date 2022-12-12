package com.github.dhslrl321.coupon.core;

import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;

public class FakeCoupons {
    public static Coupon assignedCoupon(Long couponId) {
        Coupon coupon = Coupon.newInstance(CouponId.of(couponId), CouponCode.of("1"));
        coupon.assignBy(UserId.of(123L));
        return coupon;
    }

    public static Coupon assignedCoupon(CouponId couponId) {
        Coupon coupon = Coupon.newInstance(couponId, CouponCode.of("1"));
        coupon.assignBy(UserId.of(123L));
        return coupon;
    }
}
