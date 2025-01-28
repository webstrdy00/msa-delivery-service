package com.example.coupon.couponconsumer.aplication;

import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;
import com.example.coupon.couponconsumer.aplication.ports.input.message.listener.IssueRequestMessageListener;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueRequestMessageListenerImpl implements IssueRequestMessageListener {
    private final IssueRequestHelper issueRequestHelper;

    @Override
    public void completeIssueRequest(CouponIssueRequest issueRequest) {
        // 1. 쿠폰 발급 정보 저장
        // 2. 발급 완료 로그 기록
        CouponIssue couponIssue = issueRequestHelper.persistCouponIssue(issueRequest);
        log.info("user : {}, coupon : {}, issued", couponIssue.getUserId(), couponIssue.getCouponId());
    }
}
