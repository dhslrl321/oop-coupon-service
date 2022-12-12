package com.github.dhslrl321.coupon.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.value.Quantity;
import org.junit.jupiter.api.Test;

class QuantityTest {
    @Test
    void 감소할_수_있다() {
        Quantity actual = Quantity.of(100L).minus(Quantity.of(100L));

        assertThat(actual).isEqualTo(Quantity.of(0L));
    }

    @Test
    void Quantity_는_음수가_될_수_없다() {
        assertThatThrownBy(() -> Quantity.of(100L).minus(Quantity.of(101L)))
                .isInstanceOf(ArithmeticException.class);
    }
}