package com.example.coupon.couponconsumer.dataaccess.adapter;

import com.example.coupon.couponconsumer.aplication.ports.output.CouponIssueRepository;
import com.example.coupon.couponconsumer.core.entity.CouponIssue;
import com.example.coupon.couponconsumer.dataaccess.mapper.CouponIssueDataAccessMapper;
import com.example.coupon.couponconsumer.dataaccess.repository.CouponIssueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssueRepositoryImpl implements CouponIssueRepository {
    private final CouponIssueJpaRepository couponIssueJpaRepository;
    private final CouponIssueDataAccessMapper mapper;

    @Override
    public CouponIssue save(CouponIssue couponIssue) {
        // 1. 도메인 객체를 엔티티로 변환
        // 2. JPA로 저장
        // 3. 저장된 엔티티를 다시 도메인 객체로 변환하여 반환
        return mapper.couponIssueEntityToCouponIssue(
                couponIssueJpaRepository.save(
                        mapper.couponIssueToCouponIssueEntity(couponIssue)
                )
        );
    }
}
