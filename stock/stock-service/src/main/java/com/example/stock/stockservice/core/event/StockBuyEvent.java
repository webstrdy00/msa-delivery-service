package com.example.stock.stockservice.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class StockBuyEvent {    // 상품 구매 이벤트를 나타내는 도메인 이벤트 클래스
    UUID productId;
    UUID orderId;
    Long userId;
    int quantity;
}
