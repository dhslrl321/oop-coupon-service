package com.github.dhslrl321.coupon.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class Amount {
    Long value;
}