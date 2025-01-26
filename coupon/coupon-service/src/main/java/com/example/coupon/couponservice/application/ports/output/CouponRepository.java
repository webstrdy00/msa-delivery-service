package com.example.coupon.couponservice.application.ports.output;

import com.example.coupon.couponservice.domain.Coupon;

import java.util.Optional;

public interface CouponRepository {
    Coupon save(Coupon coupon);

    Optional<Coupon> findById(Long couponId);
}
