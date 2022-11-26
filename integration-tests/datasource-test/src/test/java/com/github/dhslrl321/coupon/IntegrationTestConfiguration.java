package com.github.dhslrl321.coupon;

import com.github.dhslrl321.coupon.adapter.CouponRepositoryAdapter;
import com.github.dhslrl321.coupon.adapter.CouponStockRepositoryAdapter;
import com.github.dhslrl321.coupon.application.CouponApplier;
import com.github.dhslrl321.coupon.application.CouponDispenser;
import com.github.dhslrl321.coupon.application.SimpleWalletManager;
import com.github.dhslrl321.coupon.application.WalletManager;
import com.github.dhslrl321.coupon.core.CouponRepository;
import com.github.dhslrl321.coupon.core.CouponStockRepository;
import com.github.dhslrl321.coupon.model.command.CommandFactory;
import com.github.dhslrl321.coupon.repository.SpringDataJpaCouponRepository;
import com.github.dhslrl321.coupon.repository.SpringDataJpaCouponStockRepository;
import com.github.dhslrl321.coupon.service.command.ApplyCouponService;
import com.github.dhslrl321.coupon.service.command.AssignCouponService;
import com.github.dhslrl321.coupon.service.query.QueryViewGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationTestConfiguration {

    @Bean
    public CouponRepository couponRepository(SpringDataJpaCouponRepository repository) {
        return new CouponRepositoryAdapter(repository);
    }

    @Bean
    public CouponStockRepository couponStockRepository(SpringDataJpaCouponStockRepository repository) {
        return new CouponStockRepositoryAdapter(repository);
    }

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

    @Bean
    public AssignCouponService assignCouponService(CouponDispenser couponDispenser) {
        return new AssignCouponService(new CommandFactory(), couponDispenser);
    }

    @Bean
    public ApplyCouponService applyCouponService(CouponApplier couponApplier) {
        return new ApplyCouponService(new CommandFactory(), couponApplier);
    }

    @Bean
    public QueryViewGenerator queryViewGenerator(WalletManager walletManager,
                                                 CouponRepository couponRepository,
                                                 CouponStockRepository couponStockRepository) {
        return new QueryViewGenerator(walletManager, couponStockRepository, couponRepository);
    }
}
