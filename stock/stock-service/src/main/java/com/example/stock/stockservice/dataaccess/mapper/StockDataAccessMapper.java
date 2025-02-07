package com.example.stock.stockservice.dataaccess.mapper;

import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import com.example.stock.stockservice.dataaccess.entity.OrderEntity;
import com.example.stock.stockservice.dataaccess.entity.OrderOutboxEntity;
import com.example.stock.stockservice.dataaccess.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {   // 엔티티와 도메인 객체 간의 변환을 담당하는 매퍼 클래스

    // StockEntity를 Stock 도메인 모델로 변환하는 메서드
    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .productId(stockEntity.getProductId())
                .brandId(stockEntity.getBrandId())
                .price(stockEntity.getPrice())
                .totalQuantity(stockEntity.getTotalQuantity())
                .availableQuantity(stockEntity.getAvailableQuantity())
                .build();
    }

    // Stock 도메인 모델을 StockEntity로 변환하는 메서드
    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .productId(stock.getProductId())
                .brandId(stock.getBrandId())
                .price(stock.getPrice())
                .totalQuantity(stock.getTotalQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .build();
    }

    // OrderEntity를 Order 도메인 모델로 변환하는 메서드
    public Order orderEntityToOrder(OrderEntity order) {
        return Order.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    // Order 도메인 모델을 OrderEntity로 변환하는 메서드
    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity
                .builder()
                .id(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    // OrderOutboxMessage를 엔티티로 변환
    public OrderOutboxEntity orderOutboxMessageToOrderOutboxEntity(OrderOutboxMessage orderOutboxMessage) {
        return OrderOutboxEntity.builder()
                .orderId(orderOutboxMessage.getOrderId())
                .userId(orderOutboxMessage.getUserId())
                .productId(orderOutboxMessage.getProductId())
                .quantity(orderOutboxMessage.getQuantity())
                .orderStatus(orderOutboxMessage.getOrderStatus())
                .outboxStatus(orderOutboxMessage.getOutboxStatus())
                .build();
    }

    // OrderOutboxEntity를 메시지 객체로 변환
    public OrderOutboxMessage orderOutboxEntityToOrderOutBoxMessage(OrderOutboxEntity orderOutboxEntity) {
        return OrderOutboxMessage.builder()
                .id(orderOutboxEntity.getId())
                .orderId(orderOutboxEntity.getOrderId())
                .userId(orderOutboxEntity.getUserId())
                .productId(orderOutboxEntity.getProductId())
                .quantity(orderOutboxEntity.getQuantity())
                .orderStatus(orderOutboxEntity.getOrderStatus())
                .outboxStatus(orderOutboxEntity.getOutboxStatus())
                .build();
    }
}
