package com.example.coupon.couponservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Coupon {   // 쿠폰 도메인 클래스: 쿠폰 핵심 비즈니스 로직 구현
    private Long id;
    private String title;  // 쿠폰명
    private CouponType couponType;  // 쿠폰 타입 (FIFO 등)
    private Integer totalQuantity;  // 총 발급 가능 수량
    private int issuedQuantity;   // 현재 발급된 수량
    private int discountAmount;   // 할인 금액
    private int minAvailableAmount;   // 최소 사용 가능 금액
    private LocalDateTime dateIssueStart;    // 발급 시작일
    private LocalDateTime dateIssueEnd;   // 발급 종료일

    // 쿠폰 발급 가능 여부 확인 (수량과 날짜 모두 확인)
    public boolean issuableCoupon() {
        return availableIssueDate() && availableIssueQuantity();
    }

    // 발급 가능 수량 확인
    public boolean availableIssueQuantity() {
        if (totalQuantity == null) {
            return true;
        }
        return totalQuantity > issuedQuantity;
    }

    // 발급 가능 기간 확인
    public boolean availableIssueDate() {
        LocalDateTime now = LocalDateTime.now();
        return dateIssueStart.isBefore(now) && dateIssueEnd.isAfter(now);
    }

    // 쿠폰 발급 완료 여부 확인 (기간 만료 또는 수량 소진)
    public boolean isIssueComplete() {
        LocalDateTime now = LocalDateTime.now();
        return dateIssueEnd.isBefore(now) || !availableIssueQuantity();
    }

    // 쿠폰 발급 처리(발급 조건 검증 후 수량 증가)
    public void issue() {
        if (!availableIssueQuantity()) {
            throw new RuntimeException("발급 가능 수량 초과!!");
        }
        if (!availableIssueDate()) {
            throw new RuntimeException("발급 가능한 일자가 아닙니다!");
        }
        issuedQuantity++;
    }
}
