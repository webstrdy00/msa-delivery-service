package com.example.stock.stockservice.core.outbox;

import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.core.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class OrderOutboxMessage {   // outbox 패턴을 위한 메시지 도메인 모델
    private Long id;
    private Long orderId;
    private Long userId;
    private UUID productId;
    private int quantity;
    private OrderStatus orderStatus;    // 주문 상태
    private OutboxStatus outboxStatus;  // 메시지 처리 상태
}
