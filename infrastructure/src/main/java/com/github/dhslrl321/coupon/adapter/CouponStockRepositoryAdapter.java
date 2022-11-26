package com.github.dhslrl321.coupon.adapter;

import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.core.CouponStockRepository;
import com.github.dhslrl321.coupon.entity.SpringDataJpaCouponStock;
import com.github.dhslrl321.coupon.repository.SpringDataJpaCouponStockRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CouponStockRepositoryAdapter implements CouponStockRepository {

    private final SpringDataJpaCouponStockRepository repository;

    @Override
    public List<CouponStock> findAll() {
        List<SpringDataJpaCouponStock> stocks = repository.findAll();
        return stocks.stream().map(SpringDataJpaCouponStock::convert).collect(Collectors.toList());
    }

    @Override
    public CouponStock findByCode(CouponCode couponCode) {
        SpringDataJpaCouponStock entity = repository.findByCodeWithLocking(couponCode.getValue()).orElseThrow(() ->
                new NoSuchElementException(String.format("CouponCode[%s]: 쿠폰은 존재하지 않습니다", couponCode.getValue())));
        return SpringDataJpaCouponStock.convert(entity);
    }

    @Override
    public void save(CouponStock domain) {
        repository.save(SpringDataJpaCouponStock.convert(domain));
    }
}
