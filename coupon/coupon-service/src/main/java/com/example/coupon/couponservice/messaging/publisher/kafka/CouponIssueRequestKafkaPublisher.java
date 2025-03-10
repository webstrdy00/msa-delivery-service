package com.example.coupon.couponservice.messaging.publisher.kafka;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponservice.application.ports.output.CouponIssueRequestMessagePublisher;
import com.example.coupon.couponservice.core.event.CouponIssueEvent;
import com.example.coupon.couponservice.messaging.CouponIssueMessagingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.coupon.couponcommon.infrastructure.kafka.KafkaConstants.COUPON_ISSUE_TOPIC;

@Slf4j
@RequiredArgsConstructor
@Component
public class CouponIssueRequestKafkaPublisher implements CouponIssueRequestMessagePublisher {
    private final KafkaTemplate<String, CouponIssueAvroModel> kafkaTemplate;
    private final CouponIssueMessagingMapper couponIssueMessagingMapper;

    @Override
    public void publish(CouponIssueEvent couponIssueEvent) {
        // 도메인 이벤트를 Avro 모델로 변환하여 Kafka로 발행
        kafkaTemplate.send(COUPON_ISSUE_TOPIC,
                couponIssueMessagingMapper.couponIssueEventToAvroModel(couponIssueEvent));
    }
}
