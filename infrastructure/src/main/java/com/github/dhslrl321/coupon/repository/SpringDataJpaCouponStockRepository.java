package com.github.dhslrl321.coupon.repository;

import com.github.dhslrl321.coupon.entity.SpringDataJpaCouponStock;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataJpaCouponStockRepository extends JpaRepository<SpringDataJpaCouponStock, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select cs from coupon_stock cs where cs.code=:code")
    Optional<SpringDataJpaCouponStock> findByCodeWithLocking(String code);
}
