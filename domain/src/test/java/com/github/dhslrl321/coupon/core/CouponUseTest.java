package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.core.CouponState.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.exception.AlreadyUsedException;
import com.github.dhslrl321.coupon.exception.NotAssignedException;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CouponUseTest {

    private static final String ANY_CODE = "code";
    public static final UserId USER_ID = UserId.of(123L);

    Coupon sut;

    @BeforeEach
    void setUp() {
        sut = Coupon.newInstance(CouponCode.of(ANY_CODE));

        assertThat(sut.getState()).isEqualTo(CREATED);
    }

    @Test
    void 쿠폰을_사용할_수_있다() {
        sut.assignBy(USER_ID);
        assertThat(sut.getState()).isEqualTo(CouponState.ASSIGNED);

        sut.use();

        assertThat(sut.getState()).isEqualTo(CouponState.USED);
    }

    @Test
    void 이미_사용된_쿠폰이면_사용할_수_없다() {
        sut.assignBy(USER_ID);
        sut.use();

        assertThatThrownBy(() -> sut.use())
                .isInstanceOf(AlreadyUsedException.class).hasMessage("이미 사용된 쿠폰입니다");
    }

    @Test
    void 할당되지_않은_쿠폰이면_사용될_수_없다() {
        assertThatThrownBy(() -> sut.use())
                .isInstanceOf(NotAssignedException.class).hasMessage("할당되지 않은 쿠폰입니다");
    }
}
