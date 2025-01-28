package com.example.coupon.couponconsumer.massaging.listener.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponconsumer.aplication.ports.input.message.listener.IssueRequestMessageListener;
import com.example.coupon.couponconsumer.massaging.listener.mapper.IssueRequestMessagingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssueRequestKafkaListener {   // Kafka 쿠폰 발급 요청 리스너
    private final IssueRequestMessageListener issueRequestMessageListener;
    private final IssueRequestMessagingMapper mapper;

    // 'coupon_request' 토픽 구독
    @KafkaListener(topics = "coupon_request", groupId = "spring")
    public void consumer(@Payload CouponIssueAvroModel messages) {
        // 1. Avro 메시지를 도메인  DTO로 변환
        // 2. 변환된 DTO를 리스너로 전달하여 처리
        issueRequestMessageListener.completeIssueRequest(
                mapper.issueRequestAvroModelToIssueRequest(messages)
        );
    }
}
