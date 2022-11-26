package com.github.dhslrl321.coupon.application;

import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.command.UseCouponCommand;
import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.exception.InvalidCommandException;
import com.github.dhslrl321.coupon.result.CouponAppliedResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponApplier {

    private final WalletManager walletManager;

    public CouponAppliedResult applyBy(final UseCouponCommand command) {
        preConditions(command);

        final Coupon used = walletManager.useBy(command.getUserId(), command.getCouponId());
        return CouponAppliedResult.of(used.getId(), used.getCode(), used.getUsedAt());
    }

    private void preConditions(final UseCouponCommand command) {
        final String message = String.format("쿠폰 사용 요청이 올바르지 않습니다, useCouponCommand: => [%s]", command);

        validate(!Objects.isNull(command.getUserId()), new InvalidCommandException(message));
        validate(!Objects.isNull(command.getCouponId()), new InvalidCommandException(message));
    }
}
