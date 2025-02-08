package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.OrderOutboxRepository;
import com.example.stock.stockservice.core.outbox.OrderOutboxMessage;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderOutboxRepositoryImpl implements OrderOutboxRepository {   // outbox 패턴 구현을 위한 Repository 구현체
    private final StockDataAccessMapper mapper;
    private final OrderOutBoxJpaRepository orderOutBoxJpaRepository;

    // Outbox 메시지를 데이터베이스에 저장하고 저장된 결과를 반환
    // 1. 도메인 메시지를 엔티티로 변환
    // 2. JPA를 통해 저장
    // 3. 저장된 엔티티를 다시 도메인 메시지로 변환하여 반환
    @Override
    public OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage) {
        return mapper.orderOutboxEntityToOrderOutBoxMessage(
                orderOutBoxJpaRepository.save(
                        mapper.orderOutboxMessageToOrderOutboxEntity(orderOutboxMessage)
                )
        );
    }

    // 주문 ID로 Outbox 메시지 조회
    @Override
    public Optional<OrderOutboxMessage> findByOrderId(UUID id) {
        return orderOutBoxJpaRepository.findByOrderId(id)
                .map(mapper::orderOutboxEntityToOrderOutBoxMessage);
    }
}
