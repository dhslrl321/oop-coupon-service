package com.github.dhslrl321.coupon.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.exception.CouponSoldOutException;
import com.github.dhslrl321.coupon.value.Amount;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponName;
import com.github.dhslrl321.coupon.value.Quantity;
import org.junit.jupiter.api.Test;

class CouponStockTest {

    private static final CouponName COUPON_NAME = CouponName.of("some coupon");
    private static final Amount AMOUNT = Amount.of(100L);
    private static final CouponCode COUPON_CODE = CouponCode.of("any_code");

    CouponStock sut;

    @Test
    void 쿠폰의_재고를_감소시킨다() {
        sut = CouponStock.newInstance(Quantity.of(100L), COUPON_CODE, AMOUNT, COUPON_NAME);

        sut.decrease();

        assertThat(sut.getTotalQuantity()).isEqualTo(Quantity.of(100L));
        assertThat(sut.getLeftQuantity()).isEqualTo(Quantity.of(99L));
    }

    @Test
    void 남은_쿠폰의_재고가_없는데도_감소시키려_한다면() {
        sut = CouponStock.newInstance(Quantity.of(1L), COUPON_CODE, AMOUNT, COUPON_NAME);

        sut.decrease();
        assertThatThrownBy(() -> sut.decrease())
                .isInstanceOf(CouponSoldOutException.class).hasMessage("남은 쿠폰 재고가 존재하지 않습니다");
    }
}