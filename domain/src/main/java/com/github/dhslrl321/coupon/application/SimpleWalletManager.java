package com.github.dhslrl321.coupon.application;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.core.CouponId;
import com.github.dhslrl321.coupon.core.CouponRepository;
import com.github.dhslrl321.coupon.core.UserId;
import com.github.dhslrl321.coupon.core.Wallet;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleWalletManager implements WalletManager {

    private final CouponRepository repository;

    @Override
    public void add(final UserId userId, final Coupon coupon) {
        final List<Coupon> coupons = repository.findAllBy(userId);
        final Wallet wallet = makeWallet(userId, coupons);
        wallet.add(coupon);
        repository.saveAll(coupons);
    }

    @Override
    public Coupon useBy(final UserId userId, final CouponId couponId) {
        final List<Coupon> coupons = repository.findAllBy(userId);
        final Wallet wallet = makeWallet(userId, coupons);
        wallet.useBy(couponId);

        final List<Coupon> saved = wallet.getCoupons();
        repository.saveAll(saved);
        return wallet.getBy(couponId);
    }

    @Override
    public List<Coupon> getAllBy(final UserId userId) {
        return repository.findAllBy(userId);
    }

    private Wallet makeWallet(final UserId userId, final List<Coupon> coupons) {
        return Wallet.of(userId, coupons);
    }
}
