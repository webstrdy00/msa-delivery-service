package com.example.coupon.couponconsumer.aplication.mapper;

import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import org.springframework.stereotype.Component;

@Component
public class CouponIssueMapper {
    // DTO를 도메인 엔티티로 변환하는 매퍼
    public CouponIssue issueRequestToCouponIssue(CouponIssueRequest issueRequest) {
        return CouponIssue
                .builder()
                .userId(issueRequest.userId())      // 사용자 ID 매핑
                .couponId(issueRequest.couponId())  // 쿠폰 ID 매핑
                .build();
    }
}
