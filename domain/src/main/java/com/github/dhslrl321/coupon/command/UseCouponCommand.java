package com.github.dhslrl321.coupon.command;

import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;
import lombok.Value;

@Value(staticConstructor = "of")
public class UseCouponCommand {
    UserId userId;
    CouponId couponId;
}
