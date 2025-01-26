package com.example.coupon.couponservice.dataaccess.repository;

import com.example.coupon.couponservice.dataaccess.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
}
