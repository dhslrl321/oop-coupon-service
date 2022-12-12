package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.core.FakeCoupons.assignedCoupon;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WalletUseTest {

    public static final CouponId COUPON_ID = CouponId.of(123L);
    public static final CouponId OTHER_COUPON_ID = CouponId.of(999L);
    Wallet sut;

    @BeforeEach
    void setUp() {
        sut = Wallet.of(UserId.of(1L));
    }

    @Test
    void couponId_가_있다면_쿠폰을_사용할_수_있다() {
        initWalletWith(assignedCoupon(COUPON_ID));
        assertThat(sut.countOfUsableCoupons()).isOne();

        sut.useBy(COUPON_ID);

        assertThat(sut.countOfUsableCoupons()).isZero();
    }

    @Test
    void 존재하지_않는_쿠폰은_사용할_수_없다() {
        initWalletWith(assignedCoupon(COUPON_ID));
        assertThat(sut.countOfUsableCoupons()).isOne();

        assertThatThrownBy(() -> sut.useBy(OTHER_COUPON_ID))
                .isInstanceOf(NoSuchElementException.class).hasMessage("couponId: [999] 쿠폰이 존재하지 않습니다");
    }

    @Test
    void 쿠폰을_조회할_수_있다() {
        Coupon coupon = assignedCoupon(COUPON_ID);
        initWalletWith(coupon);

        Coupon actual = sut.getBy(COUPON_ID);

        assertThat(actual.getId()).isEqualTo(coupon.getId());
    }

    private void initWalletWith(Coupon... provided) {
        Arrays.stream(provided).forEach(c -> sut.add(c));
    }
}
