package com.github.dhslrl321.coupon.core;

import java.util.List;

public interface CouponRepository {
    Coupon findById(CouponId couponId);

    List<Coupon> findAllBy(UserId userId);

    void saveAll(List<Coupon> coupons);
}
