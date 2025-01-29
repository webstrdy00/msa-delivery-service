package com.example.coupon.couponservice.application.ports.input.web;

public record IssueRequestCommand(
        Long couponId,
        Long userId
) {
}
