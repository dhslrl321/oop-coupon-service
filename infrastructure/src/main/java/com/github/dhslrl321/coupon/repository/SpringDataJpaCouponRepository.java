package com.github.dhslrl321.coupon.repository;

import com.github.dhslrl321.coupon.entity.SpringDataJpaCoupon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCouponRepository extends JpaRepository<SpringDataJpaCoupon, Long> {
    List<SpringDataJpaCoupon> findAllByAssignee(Long assigneeId);
}
