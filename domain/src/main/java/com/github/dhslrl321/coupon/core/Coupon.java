package com.github.dhslrl321.coupon.core;

import static com.github.dhslrl321.coupon.core.CouponState.CREATED;
import static com.github.dhslrl321.coupon.core.CouponState.USED;
import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.exception.AlreadyAssignedException;
import com.github.dhslrl321.coupon.exception.AlreadyUsedException;
import com.github.dhslrl321.coupon.exception.NotAssignedException;
import com.github.dhslrl321.coupon.util.TimeBasedUniqueIdGenerator;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon {

    @Deprecated(since = "for test")
    public static Coupon newInstance(CouponId couponId, CouponCode code) {
        return new Coupon(couponId, code);
    }

    public static Coupon newInstance(CouponCode code) {
        Long gen = TimeBasedUniqueIdGenerator.getInstance().gen();
        return new Coupon(CouponId.of(gen), code);
    }

    public static Coupon from(CouponId couponId, CouponCode code, UserId assignee, LocalDateTime assignedAt,
                              LocalDateTime usedAt, CouponState state) {
        return new Coupon(couponId, code, assignee, assignedAt, usedAt, state);
    }

    private final CouponId id;
    private final CouponCode code;
    private UserId assignee; // possible NPE !
    private LocalDateTime assignedAt;
    private LocalDateTime usedAt;
    private CouponState state = CouponState.CREATED;

    private Coupon(CouponId id, CouponCode code) {
        this.id = id;
        this.code = code;
    }

    public void assignBy(final UserId assignee) {
        validate(Objects.isNull(this.assignee), new AlreadyAssignedException("이미 발급된 쿠폰입니다"));

        this.assignedAt = LocalDateTime.now();
        this.assignee = assignee;
        this.state = CouponState.ASSIGNED;
    }

    public void use() {
        validate(!CREATED.equals(state), new NotAssignedException("할당되지 않은 쿠폰입니다"));
        validate(!USED.equals(state), new AlreadyUsedException("이미 사용된 쿠폰입니다"));

        this.state = USED;
        this.usedAt = LocalDateTime.now();
    }
}
