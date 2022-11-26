package com.github.dhslrl321.coupon.core;

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
