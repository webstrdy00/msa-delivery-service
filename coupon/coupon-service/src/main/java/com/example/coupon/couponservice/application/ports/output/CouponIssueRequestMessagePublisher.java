package com.example.coupon.couponservice.application.ports.output;

import com.example.coupon.couponservice.core.event.CouponIssueEvent;

public interface CouponIssueRequestMessagePublisher {
    void publish(CouponIssueEvent couponIssueEvent);
}
