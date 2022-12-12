package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.core.CouponState.ASSIGNED;
import static com.github.dhslrl321.coupon.core.CouponState.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.exception.AlreadyAssignedException;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CouponAssignTest {

    private static final String ANY_CODE = "code";

    Coupon sut;

    @BeforeEach
    void setUp() {
        sut = Coupon.newInstance(CouponCode.of(ANY_CODE));

        assertThat(sut.getState()).isEqualTo(CREATED);
    }

    @Test
    void coupon_은_user_에게_할당된다() {
        sut.assignBy(UserId.of(123L));

        assertThat(sut.getAssignee()).isEqualTo(UserId.of(123L));
        assertThat(sut.getState()).isEqualTo(ASSIGNED);
        assertThat(sut.getAssignedAt()).isNotNull();
    }

    @Test
    void 중복할당은_불가능하다() {
        sut.assignBy(UserId.of(123L));

        assertThatThrownBy(() -> sut.assignBy(UserId.of(123L)))
                .isInstanceOf(AlreadyAssignedException.class).hasMessage("이미 발급된 쿠폰입니다");
    }
}