package com.github.dhslrl321.coupon.service.query;

import com.github.dhslrl321.coupon.application.WalletManager;
import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.repository.CouponRepository;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.repository.CouponStockRepository;
import com.github.dhslrl321.coupon.value.UserId;
import com.github.dhslrl321.coupon.model.data.CouponDetailData;
import com.github.dhslrl321.coupon.model.data.CouponDetailWithUserData;
import com.github.dhslrl321.coupon.model.data.CouponDetailsData;
import com.github.dhslrl321.coupon.model.data.CouponStockDetailData;
import com.github.dhslrl321.coupon.model.data.CouponStockDetailsData;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryViewGenerator {

    private final WalletManager walletManager;
    private final CouponStockRepository couponStockRepository;
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    public CouponDetailsData userCoupons(final Long userId) {
        List<Coupon> coupons = walletManager.getAllBy(UserId.of(userId));
        return CouponDetailsData.of(coupons.stream().map(this::toCouponDetail).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public CouponStockDetailsData couponStocks() {
        List<CouponStock> stocks = couponStockRepository.findAll();
        return CouponStockDetailsData.of(stocks.stream().map(this::toStockDetail).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public CouponDetailWithUserData couponBy(final Long couponId) {
        Coupon coupon = couponRepository.findById(CouponId.of(couponId));
        return CouponDetailWithUserData.of(coupon.getAssignee().getValue(), coupon.getId().getValue(),
                coupon.getCode().getValue(), coupon.getState().name(), coupon.getAssignedAt(), coupon.getUsedAt());
    }

    private CouponStockDetailData toStockDetail(CouponStock s) {
        return CouponStockDetailData.of(s.getCode().getValue(),
                s.getName().getValue(), s.getAmount().getValue(),
                s.getTotalQuantity().getValue(),
                s.getLeftQuantity().getValue());
    }

    private CouponDetailData toCouponDetail(Coupon c) {
        return CouponDetailData.of(c.getId().getValue(), c.getCode().getValue(), c.getState().name(),
                c.getAssignedAt(), c.getUsedAt());
    }
}
