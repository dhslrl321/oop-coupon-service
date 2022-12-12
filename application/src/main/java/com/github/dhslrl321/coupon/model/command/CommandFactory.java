package com.github.dhslrl321.coupon.model.command;

import com.github.dhslrl321.coupon.command.AssignCouponCommand;
import com.github.dhslrl321.coupon.command.UseCouponCommand;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;
import com.github.dhslrl321.coupon.model.data.ApplyCouponRequestData;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
    public UseCouponCommand create(ApplyCouponRequestData data) {
        return UseCouponCommand.of(UserId.of(data.getUserId()), CouponId.of(data.getCouponId()));
    }

    public AssignCouponCommand create(AssignCouponRequestData data) {
        return AssignCouponCommand.of(UserId.of(data.getUserId()), CouponCode.of(data.getCouponCode()));
    }
}
