package com.github.dhslrl321.coupon.service.command;

import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.application.CouponDispenser;
import com.github.dhslrl321.coupon.command.AssignCouponCommand;
import com.github.dhslrl321.coupon.model.command.CommandFactory;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAssignedResultData;
import com.github.dhslrl321.coupon.result.CouponAssignedResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssignCouponService {
    private final CommandFactory factory;
    private final CouponDispenser couponDispenser;

    @Transactional
    public CouponAssignedResultData assign(final AssignCouponRequestData data) {
        preConditions(data);

        final AssignCouponCommand command = factory.create(data);
        final CouponAssignedResult result = couponDispenser.assign(command);
        return convert(result);
    }

    private void preConditions(final AssignCouponRequestData data) {
        validate(!Objects.isNull(data.getUserId()), new IllegalArgumentException());
        validate(!Objects.isNull(data.getCouponCode()), new IllegalArgumentException());
    }

    private CouponAssignedResultData convert(final CouponAssignedResult result) {
        return CouponAssignedResultData.of(result.getCouponId().getValue(), result.getCouponCode().getValue(),
                result.getAssignedAt());
    }
}
