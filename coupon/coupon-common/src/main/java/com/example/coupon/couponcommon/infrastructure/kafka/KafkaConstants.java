package com.example.coupon.couponcommon.infrastructure.kafka;
// Kafka 관련 상수 정의 클래스
public class KafkaConstants {
    private KafkaConstants() {
    }

    // 쿠폰 발급 요청을 처리하는 Kafka 토픽명
    public static final String COUPON_ISSUE_TOPIC = "coupon_request";
}
