package com.example.stock.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {     // 분산 락 적용하기 위한 커스텀 어노테이션
    TimeUnit timeUnit() default TimeUnit.SECONDS;   // 시간 단위

    long waitTime() default 5L;     // 락 획득 대기 시간

    long leaseTime() default 5L;    // 락 유지 시간
}
