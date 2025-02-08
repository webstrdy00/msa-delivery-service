package com.example.stock.stockservice.dataaccess.adapter;

import com.example.stock.stockservice.application.ports.output.OrderRepository;
import com.example.stock.stockservice.core.Order;
import com.example.stock.stockservice.dataaccess.mapper.StockDataAccessMapper;
import com.example.stock.stockservice.dataaccess.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {  // 도메인 모델 Repository 구현체

    private final OrderJpaRepository orderJpaRepository;
    private final StockDataAccessMapper mapper;

    // 주문을 저장하고 저장된 주문 정보를 반환하는 메서드
    @Override
    public Order save(Order order) {
        return mapper.orderEntityToOrder(
                orderJpaRepository.save(
                        mapper.orderToOrderEntity(order)
                )
        );
    }

    // ID로 주문 조회 메서드
    @Override
    public Optional<Order> findById(UUID id) {
        return orderJpaRepository.findById(id)
                .map(mapper::orderEntityToOrder);
    }
}
