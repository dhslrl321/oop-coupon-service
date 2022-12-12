package com.github.dhslrl321.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dhslrl321.coupon.core.Coupon;
import com.github.dhslrl321.coupon.value.CouponCode;
import com.github.dhslrl321.coupon.value.CouponId;
import com.github.dhslrl321.coupon.repository.CouponRepository;
import com.github.dhslrl321.coupon.core.CouponState;
import com.github.dhslrl321.coupon.core.CouponStock;
import com.github.dhslrl321.coupon.value.CouponStockId;
import com.github.dhslrl321.coupon.repository.CouponStockRepository;
import com.github.dhslrl321.coupon.value.UserId;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(classes = {IntegrationTestConfiguration.class})
@EnableAutoConfiguration
@DataJpaTest
class DataSourceIntegrationTest {

    @Autowired
    CouponStockRepository couponStockRepository;

    @Autowired
    CouponRepository couponRepository;

    @Test
    @Transactional
    void 저장할_수_있다() {
        CouponStockId couponStockId = CouponStockId.of(1L);

        couponStockRepository.save(CouponStocks.couponStock(couponStockId));

        CouponStock actual = couponStockRepository.findByCode(CouponCode.of("C0001"));

        assertThat(actual.getId()).isEqualTo(couponStockId);
    }

    @Test
    @Transactional
    void 저장할_수_있다2() {
        couponRepository.saveAll(Arrays.asList(Coupons.coupon(CouponId.of(1L), CouponState.ASSIGNED)));

        List<Coupon> actual = couponRepository.findAllBy(UserId.of(1004L));

        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getId()).isEqualTo(CouponId.of(1L));
    }

    @Test
    @Transactional
    void dirty_checking() {

        Coupon c1 = Coupons.coupon(CouponId.of(1L), CouponState.ASSIGNED);
        Coupon c2 = Coupons.coupon(CouponId.of(2L), CouponState.USED);
        couponRepository.saveAll(Arrays.asList(c1, c2));

        c1.use();

        couponRepository.saveAll(Arrays.asList(c1, c2));
        List<Coupon> actual = couponRepository.findAllBy(UserId.of(1004L));

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getState()).isEqualTo(CouponState.USED);
    }
}