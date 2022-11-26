package com.github.dhslrl321.coupon.application;

import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.command.AssignCouponCommand;
import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.core.CouponStockRepository;
import com.github.dhslrl321.coupon.exception.InvalidCommandException;
import com.github.dhslrl321.coupon.result.CouponAssignedResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponDispenser {

    private final CouponStockRepository couponStockRepository;
    private final WalletManager walletManager;

    public CouponAssignedResult assign(final AssignCouponCommand command) {

        preConditions(command);

        decreaseCouponStock(command);
        final Coupon coupon = createThenAssignBy(command);
        walletManager.add(command.getUserId(), coupon);

        return CouponAssignedResult.of(coupon.getId(), coupon.getCode(), coupon.getAssignedAt());
    }

    private void preConditions(final AssignCouponCommand command) {
        final String message = String.format("쿠폰 생성 요청이 올바르지 않습니다, useCouponCommand: => [%s]", command);

        validate(!Objects.isNull(command.getUserId()), new InvalidCommandException(message));
        validate(!Objects.isNull(command.getCouponCode()), new InvalidCommandException(message));
    }

    private void decreaseCouponStock(final AssignCouponCommand command) {
        final CouponStock couponStock = couponStockRepository.findByCode(command.getCouponCode());
        couponStock.decrease();
        couponStockRepository.save(couponStock);
    }

    private Coupon createThenAssignBy(final AssignCouponCommand command) {
        final Coupon coupon = Coupon.newInstance(command.getCouponCode());
        coupon.assignBy(command.getUserId());
        return coupon;
    }
}
