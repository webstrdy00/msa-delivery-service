package com.example.stock.stockservice.application.ports.input.web;

import com.example.stock.stockservice.core.enums.OrderStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderStatusResponse(  // 주문 상태 조회에 대한 응답 데이터 클래스
        Long id,
        UUID productId,
        int quantity,
        OrderStatus orderStatus
) {

}
