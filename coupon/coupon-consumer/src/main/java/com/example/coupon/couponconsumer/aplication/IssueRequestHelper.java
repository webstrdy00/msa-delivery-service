package com.example.coupon.couponconsumer.aplication;

import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.aplication.mapper.CouponIssueMapper;
import com.example.coupon.couponconsumer.aplication.ports.output.CouponIssueRepository;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class IssueRequestHelper {
    private final CouponIssueRepository couponIssueRepository;
    private final CouponIssueMapper mapper;

    @Transactional
    public CouponIssue persistCouponIssue(CouponIssueRequest issueRequest) {
        // 쿠폰 발급 요청을 영구 저장
        return couponIssueRepository.save(mapper.issueRequestToCouponIssue(issueRequest));
    }
}
