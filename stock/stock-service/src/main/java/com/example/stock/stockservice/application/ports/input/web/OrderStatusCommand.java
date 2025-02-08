package com.example.stock.stockservice.application.ports.input.web;

import java.util.UUID;

public record OrderStatusCommand(   // 주문 상태 조회를 위한 요청 데이터 클래스
        UUID orderId
) {
}
