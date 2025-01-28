package com.example.coupon.couponconsumer.aplication.ports.output;

import com.example.coupon.couponconsumer.core.entity.CouponIssue;

public interface CouponIssueRepository {
    CouponIssue save(CouponIssue couponIssue);
}
