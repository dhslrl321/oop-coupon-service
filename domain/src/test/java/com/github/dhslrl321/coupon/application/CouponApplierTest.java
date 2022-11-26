package com.github.dhslrl321.coupon.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.github.dhslrl321.coupon.command.UseCouponCommand;
import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.UserId;
import com.github.dhslrl321.coupon.core.Wallet;
import com.github.dhslrl321.coupon.exception.InvalidCommandException;
import com.github.dhslrl321.coupon.result.CouponAppliedResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CouponApplierTest {

    public static final UserId USER_ID = UserId.of(1L);
    public static final CouponId COUPON_ID = CouponId.of(100L);

    @InjectMocks
    CouponApplier sut;

    @Mock
    WalletManager walletManager;

    @Test
    void 쿠폰을_사용할_수_있다() {
        setUpWalletWith(USER_ID, COUPON_ID);
        UseCouponCommand command = UseCouponCommand.of(USER_ID, COUPON_ID);

        CouponAppliedResult actual = sut.applyBy(command);

        assertThat(actual.getCouponId()).isEqualTo(COUPON_ID);
        assertThat(actual.getCouponCode()).isEqualTo(CouponCode.of("A"));
        assertThat(actual.getUsedAt()).isNotNull();
    }

    @Nested
    class 사전_조건을_만족한다 {
        @Test
        void userId_는_null_일_수_없다() {
            UseCouponCommand command = UseCouponCommand.of(null, COUPON_ID);

            assertThatThrownBy(() -> sut.applyBy(command)).isInstanceOf(InvalidCommandException.class);
        }

        @Test
        void couponId_는_null_일_수_없다() {
            UseCouponCommand command = UseCouponCommand.of(UserId.of(1L), null);

            assertThatThrownBy(() -> sut.applyBy(command)).isInstanceOf(InvalidCommandException.class);
        }
    }

    private void setUpWalletWith(UserId userId, CouponId couponId) {
        Wallet wallet = Wallet.of(userId);

        Coupon c1 = Coupon.newInstance(COUPON_ID, CouponCode.of("A"));
        c1.assignBy(userId);
        c1.use();
        wallet.add(c1);

        given(walletManager.useBy(userId, couponId)).willReturn(c1);
    }
}