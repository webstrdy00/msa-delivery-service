package com.example.coupon.couponservice.application.ports.output;

public interface AtomicCouponIssueRepository {
    void issueRequest(Long couponId, Long userId, int totalIssueQuantity);
}
