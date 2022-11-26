package com.github.dhslrl321.coupon.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UseCouponSuccessResponse {
    Long couponId;
    String code;
    LocalDateTime usedAt;
}
