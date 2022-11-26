package com.github.dhslrl321.coupon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.dhslrl321.coupon.core.CouponCode;
import com.github.dhslrl321.coupon.entity.SpringDataJpaCouponStock;
import com.github.dhslrl321.coupon.exception.CouponSoldOutException;
import com.github.dhslrl321.coupon.model.data.AssignCouponRequestData;
import com.github.dhslrl321.coupon.repository.SpringDataJpaCouponStockRepository;
import com.github.dhslrl321.coupon.service.command.AssignCouponService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(classes = {IntegrationTestConfiguration.class})
@EnableAutoConfiguration
@DataJpaTest
public class FirstComeFirstServedCouponIntegrationTest {
    public static final CouponCode COUPON_CODE = CouponCode.of("C0001");
    public static final long COUPON_STOCK_ID = 1L;

    @Autowired
    AssignCouponService service;

    @Autowired
    SpringDataJpaCouponStockRepository repository;

    @BeforeEach
    void setUp() {
        SpringDataJpaCouponStock entity = SpringDataJpaCouponStock.builder()
                .id(COUPON_STOCK_ID)
                .code(COUPON_CODE.getValue())
                .name("의류 할인 쿠폰")
                .totalQuantity(100L)
                .leftQuantity(100L)
                .amount(50000L)
                .build();
        repository.save(entity);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("[단일 스레드] 시작 100 개 -> 100 개 요청")
    void 단일_스레드에서_순차적으로_100개_요청() {
        for (int i = 0; i < 100; i++) {
            service.assign(AssignCouponRequestData.of((long) i, COUPON_CODE.getValue()));
        }

        SpringDataJpaCouponStock actual = repository.findById(COUPON_STOCK_ID).orElseThrow();

        assertThat(actual.getLeftQuantity()).isZero();
    }

    @Test
    @Transactional
    @DisplayName("[단일 스레드] 시작 100 개 -> 101 개 요청")
    void 단일_스레드에서_101개_요청() {
        for (int i = 0; i < 100; i++) {
            service.assign(AssignCouponRequestData.of((long) i, COUPON_CODE.getValue()));
        }

        assertThatThrownBy(() -> service.assign(AssignCouponRequestData.of(101L, COUPON_CODE.getValue())))
                .isInstanceOf(CouponSoldOutException.class).hasMessage("남은 쿠폰 재고가 존재하지 않습니다");
    }

    @Test
    @Transactional
    @Disabled
    @DisplayName("[멀티 스레드] 시작 100 개 -> 101 개 요청")
    void 멀티_스레드에서_100개_요청() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(100);

        for (long i = 0; i < 100; i++) {
            long finalI = i;
            executorService.submit(() -> {
                try {
                    service.assign(AssignCouponRequestData.of(finalI, COUPON_CODE.getValue()));
                    /*SpringDataJpaCouponStock stock = repository.findById(1L).orElseThrow();
                    System.out.printf("[%s]: 발급된 쿠폰 수 [%d] || 남은 쿠폰 수 [%d] %n", Thread.currentThread().getName(), (stock.getTotalQuantity() - stock.getLeftQuantity()), stock.getLeftQuantity());*/
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        SpringDataJpaCouponStock actual = repository.findById(COUPON_STOCK_ID).orElseThrow();

        assertThat(actual.getLeftQuantity()).isEqualTo(0);
    }
}
