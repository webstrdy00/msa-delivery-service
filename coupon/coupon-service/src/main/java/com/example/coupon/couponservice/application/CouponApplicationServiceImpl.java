package com.example.coupon.couponservice.application;

import com.example.coupon.couponservice.application.ports.input.CouponApplicationService;
import com.example.coupon.couponservice.application.ports.output.AtomicCouponIssueRepository;
import com.example.coupon.couponservice.application.ports.output.CouponIssueRequestMessagePublisher;
import com.example.coupon.couponservice.application.ports.output.CouponRepository;
import com.example.coupon.couponservice.core.Coupon;
import com.example.coupon.couponservice.core.event.CouponIssueEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponApplicationServiceImpl implements CouponApplicationService {   // 쿠폰 발급 비즈니스 로직 구현 서비스
    private final CouponRepository couponRepository;
    private final AtomicCouponIssueRepository atomicCouponIssueRepository;
    private final CouponIssueRequestMessagePublisher couponIssueRequestMessagePublisher;

    // 쿠폰 발급 처리
    @Override
    public void issue(Long couponId, Long userId) {
        // 1. 쿠폰 조회 및 발급 가능 여부 확인(유효성 검증)
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon 이 없습니다."));
        if (!coupon.issuableCoupon()) {
            throw new RuntimeException("Not Issuable Coupon");
        }

        // 2. Redis를 통한 동시성 제어
        atomicCouponIssueRepository.issueRequest(couponId, userId, coupon.getTotalQuantity());

        // 3. Kafka로 쿠폰 발급 이벤트 발행
        couponIssueRequestMessagePublisher.publish(CouponIssueEvent
                .builder()
                .couponId(couponId)
                .userId(userId)
                .build());
    }
}
