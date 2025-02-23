package com.example.stock.paymentservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class PaymentResponse {
    UUID productId;
    UUID orderId;
    Long userId;
    Long paymentId;
    int quantity;
    boolean result; // 결제 처리 결과
}
