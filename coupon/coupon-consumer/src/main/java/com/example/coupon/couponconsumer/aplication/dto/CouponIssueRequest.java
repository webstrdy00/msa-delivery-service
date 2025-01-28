package com.example.coupon.couponconsumer.aplication.dto;
// 쿠폰 발급 요청 DTO (Record 타입 사용)
public record CouponIssueRequest(
        Long couponId,
        Long userId
) {

}
