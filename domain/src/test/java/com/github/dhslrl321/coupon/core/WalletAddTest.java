package com.github.dhslrl321.coupon.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.exception.DuplicateCouponAddException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalletAddTest {

    public static final CouponCode COUPON_CODE_1 = CouponCode.of("code 1");
    public static final CouponCode COUPON_CODE_2 = CouponCode.of("code 2");
    public static final UserId ANY_USER_ID = UserId.of(1L);

    Wallet sut;

    @BeforeEach
    void setUp() {
        sut = Wallet.of(ANY_USER_ID);
    }

    @Test
    void wallet_은_쿠폰을_가지고있다() {
        assertThat(sut.countOfCoupons()).isZero();
        sut.add(Coupon.newInstance(COUPON_CODE_1));
        assertThat(sut.countOfCoupons()).isOne();
    }

    @Test
    void 동일한_코드의_쿠폰은_2개_이상_가질_수_없다() {
        sut.add(Coupon.newInstance(COUPON_CODE_1));

        assertThatThrownBy(() -> sut.add(Coupon.newInstance(COUPON_CODE_1)))
                .isInstanceOf(DuplicateCouponAddException.class);
    }

    @Test
    void 서로_다른_코드의_쿠폰은_여러장_가질_수_있다() {
        sut.add(Coupon.newInstance(COUPON_CODE_1));
        sut.add(Coupon.newInstance(COUPON_CODE_2));

        assertThat(sut.countOfCoupons()).isEqualTo(2);
    }
}