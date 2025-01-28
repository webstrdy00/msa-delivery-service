package com.example.coupon.couponconsumer.aplication.ports.input.message.listener;

import com.example.coupon.couponconsumer.aplication.dto.CouponIssueRequest;

public interface IssueRequestMessageListener {
    void completeIssueRequest(CouponIssueRequest issueRequest);
}
