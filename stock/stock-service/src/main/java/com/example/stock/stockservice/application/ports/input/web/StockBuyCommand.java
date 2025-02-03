package com.example.stock.stockservice.application.ports.input.web;

import java.util.UUID;

public record StockBuyCommand(  // 상품 구매 요청을 위한 데이터 클래스
        UUID productId,
        int quantity
) {
}
