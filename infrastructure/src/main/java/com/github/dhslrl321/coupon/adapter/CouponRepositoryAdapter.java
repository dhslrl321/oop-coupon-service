package com.github.dhslrl321.coupon.adapter;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.CouponRepository;
import com.github.dhslrl321.coupon.core.UserId;
import com.github.dhslrl321.coupon.entity.SpringDataJpaCoupon;
import com.github.dhslrl321.coupon.repository.SpringDataJpaCouponRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryAdapter implements CouponRepository {

    private final SpringDataJpaCouponRepository repository;

    @Override
    public Coupon findById(CouponId couponId) {
        SpringDataJpaCoupon found = repository.findById(couponId.getValue())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("couponId [%d] 은 존재하지 않는 쿠폰입니다", couponId.getValue())));
        return SpringDataJpaCoupon.convert(found);
    }

    @Override
    public List<Coupon> findAllBy(UserId userId) {
        List<SpringDataJpaCoupon> coupons = repository.findAllByAssignee(userId.getValue());
        return coupons.stream()
                .map(SpringDataJpaCoupon::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Coupon> coupons) {
        repository.saveAll(coupons.stream()
                .map(SpringDataJpaCoupon::convert)
                .collect(Collectors.toList()));
    }
}
