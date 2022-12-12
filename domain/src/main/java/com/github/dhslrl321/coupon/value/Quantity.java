package com.github.dhslrl321.coupon.value;

import static org.valid4j.Validation.validate;

import lombok.Value;

@Value(staticConstructor = "of")
public class Quantity {

    public static Quantity ZERO = Quantity.of(0L);

    Long value;

    public Quantity minus(final Quantity provided) {
        validate((value - provided.value >= 0), new ArithmeticException());
        return Quantity.of(value - provided.value);
    }
}
