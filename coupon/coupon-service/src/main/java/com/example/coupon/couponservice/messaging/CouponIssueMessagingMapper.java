package com.example.coupon.couponservice.messaging;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponservice.domain.event.CouponIssueEvent;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueMessagingMapper {
    // 도메인 이벤트를 Avro 메시지 모델로 변환하는 매퍼
    public CouponIssueAvroModel couponIssueEventToAvroModel(CouponIssueEvent couponIssueEvent) {
        // 이벤트 객체를 Avro 모델로 변환
        return CouponIssueAvroModel
                .newBuilder()
                .setUserId(couponIssueEvent.getUserId())
                .setCouponId(couponIssueEvent.getCouponId())
                .build();
    }
}
