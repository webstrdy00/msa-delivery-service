package com.example.coupon.couponservice.dataaccess.adapter;

import com.example.coupon.couponservice.application.ports.output.CouponRepository;
import com.example.coupon.couponservice.dataaccess.mapper.CouponDataAccessMapper;
import com.example.coupon.couponservice.dataaccess.repository.CouponJpaRepository;
import com.example.coupon.couponservice.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponDataAccessMapper couponDataAccessMapper;
    private final CouponJpaRepository couponJpaRepository;

    // 쿠폰 저장
    @Override
    public Coupon save(Coupon coupon) {
        // 도메인 -> 엔티티 -> 저장 -> 엔티티 -> 도메인 변환
        return couponDataAccessMapper.couponEntityToCoupon(
                couponJpaRepository.save(couponDataAccessMapper.couponToCouponEntity(coupon))
        );
    }

    // ID로 쿠폰 조회
    @Override
    public Optional<Coupon> findById(Long couponId) {
        // JPA로 조회 후 도메인 객체로 변환
        return couponJpaRepository.findById(couponId)
                .map(couponDataAccessMapper::couponEntityToCoupon);
    }
}
