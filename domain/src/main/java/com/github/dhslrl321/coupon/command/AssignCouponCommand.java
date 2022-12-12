package com.github.dhslrl321.coupon.command;

import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.UserId;
import lombok.Value;

@Value(staticConstructor = "of")
public class AssignCouponCommand {
    UserId userId;
    CouponCode couponCode;
}
