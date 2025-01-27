package com.example.coupon.couponconsumer.massaging.listener.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueRequestKafkaListener {   // Kafka 쿠폰 발급 요청 리스너
    // 'coupon_request' 토픽 구독
    @KafkaListener(topics = "coupon_request", groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {
        System.out.println("==============");
        System.out.println(messages.getUserId());
        System.out.println(messages.getCouponId());
        System.out.println("==============");
    }
}
