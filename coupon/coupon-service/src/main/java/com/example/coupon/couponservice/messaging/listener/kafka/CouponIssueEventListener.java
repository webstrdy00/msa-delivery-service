package com.example.coupon.couponservice.messaging.listener.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponservice.application.ports.output.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponIssueEventListener {
    private final CouponRepository couponRepository;

    @Transactional
    @KafkaListener(topics = "coupon_issued", groupId = "coupon-service")
    public void handleCouponIssued(@Payload CouponIssueAvroModel event) {
        // 쿠폰 수량 업데이트
        couponRepository.findById(event.getCouponId())
                .ifPresent(coupon -> {
                    coupon.issue();
                    couponRepository.save(coupon);
                });
    }
}
