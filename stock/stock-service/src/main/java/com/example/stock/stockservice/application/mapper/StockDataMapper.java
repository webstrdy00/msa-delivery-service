package com.example.stock.stockservice.application.mapper;

import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StockDataMapper {  // 비즈니스 계층의 데이터 변환을 담당하는 매퍼 클래스

    // stockBuyCommand를 stockBuyEvent로 변환하는 메서드
    public StockBuyEvent stockBuyCommandToEvent(
            StockBuyCommand stockBuyCommand,
            UUID orderId    // 주문 ID 추가
    ) {
        return StockBuyEvent
                .builder()
                .orderId(orderId)   // 생성된 주문 ID 설정
                .productId(stockBuyCommand.productId())
                .userId(stockBuyCommand.userId())
                .quantity(stockBuyCommand.quantity())
                .build();
    }

    // Order를 OrderStatusResponse로 변환하는 메서드
    public OrderStatusResponse orderToOrderStatusResponse(Order order) {
        return OrderStatusResponse.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

}
