package com.example.stock.stockservice.core.enums;

public enum OrderStatus {
    PENDING,    // 대기 중
    CANCELLED,  // 실패
    PAID,   // 결제 완료
    SHIPPING,   // 배송 중
    COMPLETE    // 배송 완료
}
