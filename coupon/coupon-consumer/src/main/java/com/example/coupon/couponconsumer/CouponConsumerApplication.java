package com.example.coupon.couponconsumer;

import com.example.coupon.couponcommon.common.CouponConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.example.coupon")  // com.example.coupon 패키지 하위 모두 스캔
@Import(CouponConfig.class)
public class CouponConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponConsumerApplication.class, args);
	}

}
