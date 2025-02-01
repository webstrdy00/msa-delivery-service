package com.example.coupon.couponconsumer.aplication;

import com.example.coupon.couponcommon.infrastructure.model.CouponIssueAvroModel;
import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.aplication.mapper.CouponIssueMapper;
import com.example.coupon.couponconsumer.aplication.ports.output.CouponIssueRepository;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.coupon.couponcommon.infrastructure.kafka.KafkaConstants.COUPON_ISSUED;

@RequiredArgsConstructor
@Component
public class IssueRequestHelper {
    private final CouponIssueRepository couponIssueRepository;
    private final CouponIssueMapper mapper;
    private final KafkaTemplate<String, CouponIssueAvroModel> kafkaTemplate;

    @Transactional
    public CouponIssue persistCouponIssue(CouponIssueRequest issueRequest) {
        // 1. 쿠폰 발급 이력 저장
        CouponIssue couponIssue = couponIssueRepository.save(
                mapper.issueRequestToCouponIssue(issueRequest)
        );

        // 2. Avro 모델 생성 및 이벤트 발행
        CouponIssueAvroModel event = CouponIssueAvroModel.newBuilder()
                .setCouponId(issueRequest.couponId())
                .setUserId(issueRequest.userId())
                .build();

        kafkaTemplate.send(COUPON_ISSUED, event);

        return couponIssue;
    }
}
