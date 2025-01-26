package com.example.coupon.couponservice.application.ports.input;

public interface CouponApplicationService {
    void issue(Long couponId, Long userId);
}
