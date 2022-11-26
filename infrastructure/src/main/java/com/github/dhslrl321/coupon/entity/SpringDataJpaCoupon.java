package com.github.dhslrl321.coupon.entity;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.CouponState;
import com.github.dhslrl321.coupon.core.UserId;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "coupon")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SpringDataJpaCoupon {
    @Id
    private Long id;
    private String code;
    private Long assignee;
    private LocalDateTime assignedAt;
    private LocalDateTime usedAt;
    private String state;

    public static SpringDataJpaCoupon convert(Coupon domain) {
        return SpringDataJpaCoupon.builder()
                .id(domain.getId().getValue())
                .code(domain.getCode().getValue())
                .assignee(domain.getAssignee().getValue())
                .assignedAt(domain.getAssignedAt())
                .usedAt(domain.getUsedAt())
                .state(domain.getState().name())
                .build();
    }

    public static Coupon convert(SpringDataJpaCoupon entity) {
        return Coupon.from(
                CouponId.of(entity.id),
                CouponCode.of(entity.code),
                UserId.of(entity.assignee),
                entity.assignedAt,
                entity.getUsedAt(),
                CouponState.valueOf(entity.state));
    }
}
