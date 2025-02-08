package com.example.stock.stockservice.core;

import com.example.stock.stockservice.core.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Order {   // 주문 정보를 관리하는 도메인 클래스
    private UUID id;    // 주문 고유 식별자
    private Long userId; // 유저 ID
    private UUID productId; // 주문한 상품 ID
    private int quantity;   // 주문 수량
    private OrderStatus orderStatus;    // 주문 상태
    private Long version;   // 낙관적 락을 위한 버전

    // 주문 상태 업데이트하는 메서드
    public Order updateStatus(OrderStatus status) {
        this.orderStatus = status;
        return this;
    }
}
