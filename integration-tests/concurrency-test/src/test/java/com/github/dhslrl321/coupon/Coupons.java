package com.github.dhslrl321.coupon;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.CouponState;
import com.github.dhslrl321.coupon.core.UserId;
import java.time.LocalDateTime;

public class Coupons {
    public static Coupon coupon(CouponId id, CouponState state) {
        return Coupon.from(id,
                CouponCode.of("C00012"),
                UserId.of(1004L),
                LocalDateTime.now(),
                LocalDateTime.now(),
                state);
    }

}
