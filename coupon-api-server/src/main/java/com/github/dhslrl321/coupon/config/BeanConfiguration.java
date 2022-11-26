package com.github.dhslrl321.coupon.config;

import com.github.dhslrl321.coupon.application.CouponApplier;
import com.github.dhslrl321.coupon.application.CouponDispenser;
import com.github.dhslrl321.coupon.application.SimpleWalletManager;
import com.github.dhslrl321.coupon.application.WalletManager;
import com.github.dhslrl321.coupon.core.CouponRepository;
import com.github.dhslrl321.coupon.core.CouponStockRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public WalletManager walletManager(CouponRepository couponRepository) {
        return new SimpleWalletManager(couponRepository);
    }

    @Bean
    public CouponApplier couponApplier(WalletManager walletManager) {
        return new CouponApplier(walletManager);
    }

    @Bean
    public CouponDispenser couponDispenser(CouponStockRepository couponStockRepository,
                                           WalletManager walletManager) {
        return new CouponDispenser(couponStockRepository, walletManager);
    }
}
