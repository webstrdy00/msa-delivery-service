package com.example.coupon.couponservice.application.ports.web;

import com.example.coupon.couponservice.application.ports.input.CouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {
    private final CouponApplicationService couponApplicationService;

    // 테스트용 쿠폰 발급 API 엔드포인트
    @GetMapping("/test")
    public void test() {
        couponApplicationService.issue(1L, 1L);
    }
}
