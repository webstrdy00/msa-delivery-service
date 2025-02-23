package com.example.stock.paymentservice.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PaymentRequest {
    private UUID productId; // 결제할 상품 ID
    private UUID orderId;   // 주문 ID
    private Long userId;    // 시용지 ID
    private int quantity;   // 결제할 상품 수량
}
