package com.github.dhslrl321.coupon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.github.dhslrl321.coupon.application.CouponDispenser;
import com.github.dhslrl321.coupon.command.AssignCouponCommand;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.value.UserId;
import com.github.dhslrl321.coupon.model.command.CommandFactory;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAssignedResultData;
import com.github.dhslrl321.coupon.result.CouponAssignedResult;
import com.github.dhslrl321.coupon.service.command.AssignCouponService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssignCouponServiceTest {

    AssignCouponService sut;

    CouponDispenser couponDispenser = mock(CouponDispenser.class);

    @BeforeEach
    void setUp() {
        sut = new AssignCouponService(new CommandFactory(), couponDispenser);
    }

    @Test
    void happy() {
        given(couponDispenser.assign(AssignCouponCommand.of(UserId.of(1L), CouponCode.of("A"))))
                .willReturn(CouponAssignedResult.of(CouponId.of(2L), CouponCode.of("A"), LocalDateTime.now()));

        CouponAssignedResultData actual = sut.assign(AssignCouponRequestData.of(1L, "A"));

        assertThat(actual.getCouponId()).isEqualTo(2L);
        assertThat(actual.getCouponCode()).isEqualTo("A");
        assertThat(actual.getAssignedAt()).isNotNull();
    }

    @Nested
    class 사전_조건을_만족한다 {
        @Test
        void userId_는_null_일_수_없다() {
            AssignCouponRequestData data = AssignCouponRequestData.of(null, "A");

            assertThatThrownBy(() -> sut.assign(data)).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void couponCode_는_null_일_수_없다() {
            AssignCouponRequestData data = AssignCouponRequestData.of(1L, null);

            assertThatThrownBy(() -> sut.assign(data)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}