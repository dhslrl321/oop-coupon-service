package com.github.dhslrl321.coupon.service.command;

import static org.valid4j.Validation.validate;

import com.github.dhslrl321.coupon.application.CouponApplier;
import com.github.dhslrl321.coupon.command.UseCouponCommand;
import com.github.dhslrl321.coupon.model.command.CommandFactory;
import com.github.dhslrl321.coupon.model.data.ApplyCouponRequestData;
import com.github.dhslrl321.coupon.model.data.CouponAppliedResultData;
import com.github.dhslrl321.coupon.result.CouponAppliedResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyCouponService {

    private final CommandFactory factory;
    private final CouponApplier applier;

    @Transactional
    public CouponAppliedResultData apply(final ApplyCouponRequestData data) {
        preConditions(data);

        final UseCouponCommand command = factory.create(data);
        final CouponAppliedResult result = applier.applyBy(command);
        return convert(result);
    }

    private void preConditions(final ApplyCouponRequestData data) {
        validate(!Objects.isNull(data.getUserId()), new IllegalArgumentException());
        validate(!Objects.isNull(data.getCouponId()), new IllegalArgumentException());
    }

    private CouponAppliedResultData convert(final CouponAppliedResult result) {
        return CouponAppliedResultData.of(result.getCouponId().getValue(), result.getCouponCode().getValue(),
                result.getUsedAt());
    }
}
