package com.github.dhslrl321.coupon.core;

import java.util.List;

public interface CouponStockRepository {
    List<CouponStock> findAll();

    CouponStock findByCode(CouponCode couponCode);

    void save(CouponStock couponStock);
}
