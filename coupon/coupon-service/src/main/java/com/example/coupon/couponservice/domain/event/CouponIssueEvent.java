package com.example.coupon.couponservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponIssueEvent {   // 쿠폰 발급 도메인 이벤트 클래스
    Long id;    // 이벤트 ID
    Long couponId;  // 쿠폰 ID
    Long userId;    // 사용자 ID

    public CouponIssueEvent(Long couponId, Long userId) {
        this.couponId = couponId;
        this.userId = userId;
    }
}
