package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.value.Quantity.ZERO;
import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.exception.CouponSoldOutException;
import com.github.dhslrl321.coupon.util.TimeBasedUniqueIdGenerator;
import com.github.dhslrl321.coupon.value.Amount;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponName;
import com.github.dhslrl321.coupon.value.CouponStockId;
import com.github.dhslrl321.coupon.value.Quantity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponStock {

    public static CouponStock newInstance(Quantity totalQuantity,
                                          CouponCode code,
                                          Amount amount,
                                          CouponName name) {
        return new CouponStock(totalQuantity, code, amount, name);
    }

    public static CouponStock from(CouponStockId id,
                                   CouponName name,
                                   CouponCode code,
                                   Amount amount,
                                   Quantity totalQuantity,
                                   Quantity leftQuantity) {
        return new CouponStock(id, name, code, amount, totalQuantity, leftQuantity);
    }

    private final CouponStockId id;
    private final CouponName name;
    private final CouponCode code;
    private final Amount amount;
    private final Quantity totalQuantity;
    private Quantity leftQuantity;

    private CouponStock(Quantity totalQuantity, CouponCode code, Amount amount,
                        CouponName name) {
        id = CouponStockId.of(TimeBasedUniqueIdGenerator.getInstance().gen());
        this.totalQuantity = totalQuantity;
        this.leftQuantity = Quantity.of(totalQuantity.getValue());
        this.code = code;
        this.amount = amount;
        this.name = name;
    }

    public void decrease() {
        validate(!ZERO.equals(leftQuantity), new CouponSoldOutException("남은 쿠폰 재고가 존재하지 않습니다"));

        leftQuantity = leftQuantity.minus(Quantity.of(1L));
    }
}
