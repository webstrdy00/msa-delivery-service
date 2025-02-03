package com.example.stock.stockservice.application.ports.mapper;

import com.example.stock.stockservice.application.ports.input.web.OrderStatusResponse;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.enums.OrderStatus;
import com.example.stock.stockservice.core.event.StockBuyEvent;
import org.springframework.stereotype.Component;

@Component
public class StockDataMapper {  // 비즈니스 계층의 데이터 변환을 담당하는 매퍼 클래스

    // StockBuyEvent를 Order로 변환하는 메서드
    public Order stockBuyEventToOrder(StockBuyEvent stockBuyEvent) {
        return Order.builder()
                .productId(stockBuyEvent.getProductId())
                .quantity(stockBuyEvent.getQuantity())
                .orderStatus(OrderStatus.PENDING)
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
