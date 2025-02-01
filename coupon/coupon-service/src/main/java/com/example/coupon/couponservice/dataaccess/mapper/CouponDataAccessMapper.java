package com.example.coupon.couponservice.dataaccess.mapper;

import com.example.coupon.couponservice.core.Coupon;
import com.example.coupon.couponservice.dataaccess.entity.CouponEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponDataAccessMapper {     // 도메인 객체와 엔티티 간 변환을 담당하는 매퍼
    // Redis에서 사용할 쿠폰 발급 요청 키 생성
    public String getIssueRequestKey(long couponId) {
        return "issue:request:couponId:%s".formatted(couponId);
    }

    // 엔티티를 도메인 객체로 전환
    public Coupon couponEntityToCoupon(CouponEntity couponEntity) {
        return Coupon.builder()
                .id(couponEntity.getId())
                .title(couponEntity.getTitle())
                .couponType(couponEntity.getCouponType())
                .totalQuantity(couponEntity.getTotalQuantity())
                .issuedQuantity(couponEntity.getIssuedQuantity())
                .discountAmount(couponEntity.getDiscountAmount())
                .minAvailableAmount(couponEntity.getMinAvailableAmount())
                .dateIssueStart(couponEntity.getDateIssueStart())
                .dateIssueEnd(couponEntity.getDateIssueEnd())
                .build();
    }

    // 도메인 객체를 엔티티로 변환
    public CouponEntity couponToCouponEntity(Coupon coupon) {
        return CouponEntity.builder()
                .id(coupon.getId())
                .title(coupon.getTitle())
                .couponType(coupon.getCouponType())
                .totalQuantity(coupon.getTotalQuantity())
                .issuedQuantity(coupon.getIssuedQuantity())
                .discountAmount(coupon.getDiscountAmount())
                .minAvailableAmount(coupon.getMinAvailableAmount())
                .dateIssueStart(coupon.getDateIssueStart())
                .dateIssueEnd(coupon.getDateIssueEnd())
                .build();
    }
}
