package com.example.stock.stockservice.application;

import com.example.stock.common.aop.DistributedLock;
import com.example.stock.common.command.StockBuyCommand;
import com.example.stock.common.infrastructure.outbox.OutboxStatus;
import com.example.stock.stockservice.application.mapper.StockDataMapper;
import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.application.ports.output.StockRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.core.Stock;
import com.example.stock.stockservice.core.enums.OrderStatus;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class StockServiceHelper {   // 재고 서비스의 핵심 비즈니스 로직을 처리하는 헬퍼 클래스

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;
    private final StockDataMapper mapper;
    private final ApplicationEventPublisher publisher;

    // 재고 정보 저장
    @Transactional
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    // 상품 구매 처리 메서드
    // 1. 재고 검증 및 업데이트
    // 2. 주문 정보 저장
    // 3. Outbox 메시지 생성 및 저장
    // 4. 이벤트 발행
    @Transactional
    public Order buy(StockBuyCommand stockBuyCommand) {
        // 재고 검증 및 업데이트
        checkQuantityAndUpdate(stockBuyCommand);

        // 주문 정보 저장 및 반환
        Order pandingOrder = orderRepository.save(
                createOrder(stockBuyCommand)
        );

        // Outbox 메시지 생성 및 저장
        // - 메시지 상태를 STARTED로 설정
        // - 주문 정보를 포함하여 저장
        orderOutboxRepository.save(
                OrderOutboxMessage.builder()
                        .orderId(pandingOrder.getId())
                        .userId(pandingOrder.getUserId())
                        .productId(pandingOrder.getProductId())
                        .quantity(pandingOrder.getQuantity())
                        .orderStatus(pandingOrder.getOrderStatus())
                        .outboxStatus(OutboxStatus.STARTED)
                        .build()
        );

        // 이벤트 발행 - 트랜잭션 완료 후 처리됨
        publisher.publishEvent(mapper.stockBuyCommandToEvent(stockBuyCommand, pandingOrder.getId()));

        return pandingOrder;
    }

    // 분산 락을 사용한 재고 확인 및 업데이트 로직
    // @DistributedLock: Redis 기반 분산 락 적용
    @DistributedLock
    private void checkQuantityAndUpdate(StockBuyCommand stockBuyCommand){
        // 상품 ID로 재고 정보 조회, 없으면 예외 발생
        Stock stock = stockRepository.findById(stockBuyCommand.productId())
                .orElseThrow(() -> new RuntimeException("Item Not Found"));

        // 구매 가능 수량 체크, 불가능하면 예외 발생
        if (!stock.isAvailableToBuy(stockBuyCommand.quantity())) {
            throw new RuntimeException("Not available to buy");
        }

        // 낙관적 락을 이용한 재고 업데이트
        int updateQuantity = stock.getAvailableQuantity() - stockBuyCommand.quantity();
        stock.updateAvailableQuantity(updateQuantity);
        stockRepository.save(stock);
    }

    // 주문 생성 로직을 별도 메서드로 분리
    private Order createOrder(StockBuyCommand stockBuyCommand) {
        UUID id = UUID.randomUUID();    // 새로운 주문 ID 생성
        return Order.builder()
                .id(id)
                .productId(stockBuyCommand.productId())
                .userId(stockBuyCommand.userId())
                .quantity(stockBuyCommand.quantity())
                .orderStatus(OrderStatus.PENDING)   // 초기 상태를 PENDING
                .build();
    }
}
