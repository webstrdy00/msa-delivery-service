package com.example.coupon.couponservice.dataaccess.adapter;

import com.example.coupon.couponservice.application.ports.output.AtomicCouponIssueRepository;
import com.example.coupon.couponservice.dataaccess.mapper.CouponDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RedisAtomicCouponIssueRepositoryImpl implements AtomicCouponIssueRepository {   // Redis를 사용한 동시성 쿠폰 발급 처리
    private final RedisTemplate<String, String> redisTemplate;
    private final CouponDataAccessMapper couponDataAccessMapper;

    // Redis Lua 스크립트를 통한 원자적 발급 처리
    @Override
    public void issueRequest(Long couponId, Long userId, int totalIssueQuantity) {
        // Redis Set을 사용한 중복 발급 방지
        String issueRequestKey = couponDataAccessMapper.getIssueRequestKey(couponId);

        // Lua 스크립트로 원자성 보장
        try {
            String code = redisTemplate.execute(
                    issueRequestScript(),
                    List.of(issueRequestKey),
                    String.valueOf(userId),
                    String.valueOf(totalIssueQuantity)
            );
            assert code != null;
            checkRequestResult(code);
        } catch (Exception e) {
            throw new RuntimeException("coupon 발행 실패!! " + e.getMessage());
        }
    }

    // Lua 스크립트 정의
    private RedisScript<String> issueRequestScript() {
        // 1 : 발급 성공, 2 : 이미 발급된, 3 : 수량 초과
        String script = """
                if redis.call('SISMEMBER', KEYS[1], ARGV[1]) == 1 then
                    return '2'
                end
                                
                if tonumber(ARGV[2]) > redis.call('SCARD', KEYS[1]) then
                    redis.call('SADD', KEYS[1], ARGV[1])
                    return '1'
                end
                                
                return '3'
                """;
        return RedisScript.of(script, String.class);
    }

    public void checkRequestResult(String code) {
        if (code.equals("3")) {
            throw new RuntimeException("발급 가능한 수량을 초과 !!");
        }
        if (code.equals("2")) {
            throw new RuntimeException("이미 발급된 쿠폰입니다.");
        }
    }
}
