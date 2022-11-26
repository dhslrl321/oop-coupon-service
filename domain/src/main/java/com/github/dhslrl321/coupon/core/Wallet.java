package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.core.CouponState.ASSIGNED;
import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.exception.DuplicateCouponAddException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Wallet {

    public static Wallet of(UserId userId) {
        return new Wallet(userId, new ArrayList<>());
    }

    public static Wallet of(UserId userId, List<Coupon> coupons) {
        return new Wallet(userId, coupons);
    }

    private final UserId userId;
    private final List<Coupon> coupons;

    public void add(Coupon coupon) {
        validate(isAssignable(coupon), new DuplicateCouponAddException("이미 소유한 쿠폰은 중복해서 발급받을 수 없습니다."));
        coupons.add(coupon);
    }

    public void useBy(CouponId couponId) {
        Coupon coupon = getBy(couponId);
        coupon.use();
    }

    public Coupon getBy(CouponId couponId) {
        return coupons.stream()
                .filter(c -> couponId.equals(c.getId()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("couponId: [%d] 쿠폰이 존재하지 않습니다", couponId.getValue())));
    }

    int countOfCoupons() {
        return coupons.size();
    }

    int countOfUsableCoupons() {
        // TODO Refactor, 쿠폰의 내부가 그대로 드러난다.
        return (int) coupons.stream()
                .filter(c -> ASSIGNED.equals(c.getState()))
                .count();
    }

    private boolean isAssignable(Coupon coupon) {
        // TODO Refactor, 쿠폰의 내부가 그대로 드러난다.
        return coupons.stream().noneMatch(c -> coupon.getCode().equals(c.getCode()));
    }
}
