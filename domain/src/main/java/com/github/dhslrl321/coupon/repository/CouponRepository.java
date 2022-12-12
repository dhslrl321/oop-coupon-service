package com.github.dhslrl321.coupon.repository;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;
import java.util.List;

public interface CouponRepository {
    Coupon findById(CouponId couponId);

    List<Coupon> findAllBy(UserId userId);

    void saveAll(List<Coupon> coupons);
}
