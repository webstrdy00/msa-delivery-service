package com.example.stock.common.aop;

import com.example.stock.common.command.StockBuyCommand;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {   // 분산 락 처리를 위한 AOP 컴포넌트

    private static final String REDIS_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;
    private final MyTransactionManager myTransactionManager;

    // @DistributedLock 어농테이션이 적용된 메서드 실행 전후 처리
    @Around("@annotation(com.example.stock.common.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        // 상품 ID를 기반으로 락 키 획득
        String key = REDIS_LOCK_PREFIX + getParameterizedReservationRequest(joinPoint).productId();
        RLock lock = redissonClient.getLock(key);

        try {
            // 락 획득 시도
            boolean isLocked = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            if (!isLocked) {
                return false;
            }
            return myTransactionManager.proceed(joinPoint);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error occurred when retrieving redisson lock");
        } finally {
            lock.unlock();
        }
    }

    private StockBuyCommand getParameterizedReservationRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StockBuyCommand stockBuyCommand = null;
        for (Object arg : args) {
            if (arg instanceof StockBuyCommand) {
                stockBuyCommand = (StockBuyCommand) arg;
                break;
            }
        }
        return stockBuyCommand;
    }
}
