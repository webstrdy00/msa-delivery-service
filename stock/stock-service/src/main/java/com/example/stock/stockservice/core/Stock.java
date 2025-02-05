package com.example.stock.stockservice.core;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Stock {   // 재고 관리 도메인 클래스
    private UUID productId;  // 상품 고유 식별자
    private String brandId;   // 브랜드 식별자
    private int price;   // 가격
    private int totalQuantity;  // 총 재고 수량
    private int availableQuantity;  // 구매 가능한 재고 수량

    // 요청된 수량만큼 구매 가능한지 확인하는 메서드
    public boolean isAvailableToBuy(int quantity) {
        return availableQuantity - quantity >= 0;   // 음수 재고 방지
    }
}
