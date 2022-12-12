package com.github.dhslrl321.coupon.entity;

import com.github.dhslrl321.coupon.value.Amount;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponName;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.value.CouponStockId;
import com.github.dhslrl321.coupon.value.Quantity;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "coupon_stock")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SpringDataJpaCouponStock {
    @Id
    private Long id;
    private String name;
    private String code;
    private Long amount;
    private Long totalQuantity;
    private Long leftQuantity;

    public static SpringDataJpaCouponStock convert(CouponStock domain) {
        return SpringDataJpaCouponStock.builder()
                .id(domain.getId().getValue())
                .name(domain.getName().getValue())
                .code(domain.getCode().getValue())
                .amount(domain.getAmount().getValue())
                .totalQuantity(domain.getTotalQuantity().getValue())
                .leftQuantity(domain.getLeftQuantity().getValue())
                .build();
    }

    public static CouponStock convert(SpringDataJpaCouponStock entity) {
        return CouponStock.from(
                CouponStockId.of(entity.getId()),
                CouponName.of(entity.getName()),
                CouponCode.of(entity.getCode()),
                Amount.of(entity.getAmount()),
                Quantity.of(entity.getTotalQuantity()),
                Quantity.of(entity.getLeftQuantity()));
    }
}
