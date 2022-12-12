package com.github.dhslrl321.coupon.repository;

import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.value.CouponCode;
import java.util.List;

public interface CouponStockRepository {
    List<CouponStock> findAll();

    CouponStock findByCode(CouponCode couponCode);

    void save(CouponStock couponStock);
}
