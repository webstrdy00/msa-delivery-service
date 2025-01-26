package com.example.coupon.couponservice.application;

import com.example.coupon.couponservice.application.ports.input.CouponApplicationService;
import com.example.coupon.couponservice.application.ports.output.AtomicCouponIssueRepository;
import com.example.coupon.couponservice.application.ports.output.CouponRepository;
import com.example.coupon.couponservice.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponApplicationServiceImpl implements CouponApplicationService {   // 쿠폰 발급 비즈니스 로직 구현 서비스
    private final CouponRepository couponRepository;
    private final AtomicCouponIssueRepository atomicCouponIssueRepository;

    // 쿠폰 발급 처리
    @Override
    public void issue(Long couponId, Long userId) {
        // 쿠폰 조회 및 발급 가능 여부 확인
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon 이 없습니다."));
        if (!coupon.issuableCoupon()) {
            throw new RuntimeException("Not Issuable Coupon");
        }
        // Redis를 통한 동시성 제어
        atomicCouponIssueRepository.issueRequest(couponId, userId, coupon.getTotalQuantity());

        // TODO : Kafka Messaging
    }
}
