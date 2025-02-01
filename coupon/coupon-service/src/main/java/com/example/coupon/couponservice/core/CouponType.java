package com.example.coupon.couponservice.core;

import jakarta.persistence.Column;

public enum CouponType {
    @Column(name = "coupon_type")
    FIFO("FIRST_COME_FIRST_SERVED");

    private final String value;

    CouponType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
