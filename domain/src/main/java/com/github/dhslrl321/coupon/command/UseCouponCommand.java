package com.github.dhslrl321.coupon.command;

import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.UserId;
import lombok.Value;

@Value(staticConstructor = "of")
public class UseCouponCommand {
    UserId userId;
    CouponId couponId;
}
