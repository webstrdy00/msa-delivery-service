package com.example.coupon.couponconsumer.massaging.listener.mapper;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;
import org.springframework.stereotype.Component;

@Component
public class IssueRequestMessagingMapper {
    // Kafka로부터 수신한 Avro 모델을 내부 도메인 DTO로 변환하는 매퍼
    public CouponIssueRequest issueRequestAvroModelToIssueRequest(CouponIssueAvroModel avroModel) {
        // Avro 모델의 데이터를 CouponIssueRequest DTO로 변환
        return new CouponIssueRequest(
                avroModel.getCouponId(),
                avroModel.getUserId()
        );
    }
}
