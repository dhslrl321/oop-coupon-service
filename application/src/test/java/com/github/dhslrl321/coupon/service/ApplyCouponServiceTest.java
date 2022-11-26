package com.github.dhslrl321.coupon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.coupon.application.CouponApplier;
import com.github.dhslrl321.coupon.command.UseCouponCommand;
import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.UserId;
import com.github.dhslrl321.coupon.model.command.CommandFactory;
import com.github.dhslrl321.coupon.model.data.ApplyCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAppliedResultData;
import com.github.dhslrl321.coupon.result.CouponAppliedResult;
import com.github.dhslrl321.coupon.service.command.ApplyCouponService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplyCouponServiceTest {

    ApplyCouponService sut;

    CouponApplier couponApplier = mock(CouponApplier.class);

    @BeforeEach
    void setUp() {
        sut = new ApplyCouponService(new CommandFactory(), couponApplier);
    }

    @Test
    void happy() {
        ApplyCouponRequestData requestData = ApplyCouponRequestData.of(1L, 1L);
        given(couponApplier.applyBy(UseCouponCommand.of(UserId.of(1L), CouponId.of(1L))))
                .willReturn(CouponAppliedResult.of(CouponId.of(1L), CouponCode.of("A"), LocalDateTime.now()));

        CouponAppliedResultData actual = sut.apply(requestData);

        assertThat(actual.getCouponId()).isEqualTo(1L);
        assertThat(actual.getCouponCode()).isEqualTo("A");
        assertThat(actual.getUsedAt()).isNotNull();
    }

    @Nested
    class 사전_조건을_만족한다 {
        @Test
        void userId_는_null_일_수_없다() {
            ApplyCouponRequestData requestData = ApplyCouponRequestData.of(null, 1L);

            assertThatThrownBy(() -> sut.apply(requestData))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void couponId_는_null_일_수_없다() {
            ApplyCouponRequestData requestData = ApplyCouponRequestData.of(null, 1L);

            assertThatThrownBy(() -> sut.apply(requestData))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}