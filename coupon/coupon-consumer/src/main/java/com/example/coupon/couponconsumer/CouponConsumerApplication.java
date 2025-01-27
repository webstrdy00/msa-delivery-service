package com.example.coupon.couponconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.coupon")  // com.example.coupon 패키지 하위 모두 스캔
public class CouponConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponConsumerApplication.class, args);
	}

}
